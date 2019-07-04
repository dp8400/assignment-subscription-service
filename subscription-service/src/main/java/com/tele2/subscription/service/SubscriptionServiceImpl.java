package com.tele2.subscription.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tele2.subscription.entity.SubscriptionEntity;
import com.tele2.subscription.exception.SubscriptionCreationFailureException;
import com.tele2.subscription.exception.SubscriptionNotFoundException;
import com.tele2.subscription.exception.SubscriptionUpdationFailureException;
import com.tele2.subscription.mapper.SubscriptionMapper;
import com.tele2.subscription.model.Subscription;
import com.tele2.subscription.repository.SubscriptionRepository;
/**
 * Service class of the application
 *
 */
@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	@Autowired
	private SubscriptionMapper mapper;

	@Cacheable(cacheNames="subscriptionCache",value="subscriptionCache",keyGenerator="customKeyGenerator")
	@HystrixCommand(commandKey = "get-subscriptions", fallbackMethod = "getCachedSubscriptions")
	@Override
	public List<Subscription> getSubscriptions() {
		log.debug("Getting all subscriptions");
		List<SubscriptionEntity> subscriptionsEntities = subscriptionRepository.findAll();
		return subscriptionsEntities.stream().map(subscription -> mapper.toSubscription(subscription))
				.collect(Collectors.toList());
	}

	@Cacheable("subscriptionCache")
	@HystrixCommand(commandKey = "get-subscription", fallbackMethod = "getCachedSubscription", ignoreExceptions = {
			SubscriptionNotFoundException.class })
	@Override
	public Subscription getSubscription(int id) {
		SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id)
				.orElseThrow(() -> new SubscriptionNotFoundException(String.valueOf(id)));
		return mapper.toSubscription(subscriptionEntity);
	}

	@HystrixCommand(commandKey = "update-subscription", fallbackMethod = "putSubscriptionFallback", ignoreExceptions = {
			SubscriptionNotFoundException.class })
	@Override
	public void putSubscription(int id, Subscription subscription) {
		SubscriptionEntity subscriptionEntity = subscriptionRepository.findById(id)
				.orElseThrow(() -> new SubscriptionNotFoundException(String.valueOf(id)));
		subscriptionEntity.setMonthlyPrice(subscription.getMonthlyPrice());
		subscriptionRepository.save(subscriptionEntity);
	}

	@HystrixCommand(commandKey = "create-subscription", fallbackMethod = "postSubscriptionsFallback")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public Subscription postSubscriptions(Subscription subscription) {
		SubscriptionEntity entity = subscriptionRepository.save(mapper.toSubscriptionEntity(subscription));
		return mapper.toSubscription(entity);
	}

	public Subscription postSubscriptionsFallback(Subscription subscription) {
		throw new SubscriptionCreationFailureException();
	}

	public void putSubscriptionFallback(int id, Subscription subscription, Throwable e) {
		throw new SubscriptionUpdationFailureException();
	}

	public List<Subscription> getCachedSubscriptions() {
		// Caching can be added and data fetched from there,if present
		return new ArrayList<Subscription>();
	}

	public Subscription getCachedSubscription(int id) {
		// Caching can be added and data fetched from there,if present
		throw new SubscriptionNotFoundException(String.valueOf(id));

	}

}
