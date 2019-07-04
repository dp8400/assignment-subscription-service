package com.tele2.subscription.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.tele2.subscription.entity.SubscriptionEntity;
import com.tele2.subscription.mapper.SubscriptionMapper;
import com.tele2.subscription.model.Subscription;
import com.tele2.subscription.repository.SubscriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest {
	@Autowired
	private SubscriptionService subscriptionService;
	@MockBean
	private SubscriptionRepository subscriptionRepository;
	@MockBean
	private SubscriptionMapper subscriptionMapper;

	private SubscriptionEntity subscriptionEntity;


	@Before
	public void setUp() throws Exception {
		subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setId(11);
		subscriptionEntity.setName("Subscription-Mobile");
		subscriptionEntity.setMonthlyPrice(111.9);
		subscriptionEntity.setLastUpdate(new Date());
	}

	@Test
	public void testGetSubscriptions() {
		List<SubscriptionEntity> result = new ArrayList<SubscriptionEntity>();
		result.add(subscriptionEntity);
		Mockito.when(subscriptionRepository.findAll()).thenReturn(result);
		
		Assert.assertEquals(result.size(), subscriptionService.getSubscriptions().size());
	}

	@Test
	public void testGetSubscription() {
		Optional<SubscriptionEntity> optional = Optional.of(subscriptionEntity);
		Mockito.when(subscriptionRepository.findById(Mockito.any(int.class))).thenReturn(optional);
		Mockito.when(subscriptionMapper.toSubscription(optional.get())).thenReturn(defaultSubscription());
		Subscription result = subscriptionService.getSubscription(11);
		Assert.assertNotNull(result);
	}

	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	public void testPostSubscriptions() {
		Mockito.when(subscriptionMapper.toSubscriptionEntity(Mockito.any(Subscription.class))).thenReturn(subscriptionEntity);
		Mockito.when(subscriptionRepository.save(Mockito.any(SubscriptionEntity.class))).thenReturn(subscriptionEntity);
		Mockito.when(subscriptionMapper.toSubscription(Mockito.any(SubscriptionEntity.class))).thenReturn(defaultSubscription());

		Assert.assertNotNull(subscriptionService.postSubscriptions(defaultSubscription()));
	}
	
	@Test(expected = AccessDeniedException.class)
	@WithMockUser
	public void testPostSubscriptionsAccessDenied() {
		Mockito.when(subscriptionMapper.toSubscriptionEntity(Mockito.any(Subscription.class))).thenReturn(subscriptionEntity);
		Mockito.when(subscriptionRepository.save(Mockito.any(SubscriptionEntity.class))).thenReturn(subscriptionEntity);
		Mockito.when(subscriptionMapper.toSubscription(Mockito.any(SubscriptionEntity.class))).thenReturn(defaultSubscription());

		Assert.assertNull(subscriptionService.postSubscriptions(defaultSubscription()));
	}

	@Test
	public void testPutSubscription() {
		Optional<SubscriptionEntity> optional = Optional.of(subscriptionEntity);
		Mockito.when(subscriptionRepository.findById(Mockito.any(int.class))).thenReturn(optional);
		Mockito.when(subscriptionRepository.save(Mockito.any(SubscriptionEntity.class))).thenReturn(subscriptionEntity);
		Subscription modSubscription = defaultSubscription();
		modSubscription.setMonthlyPrice(21.9);
		subscriptionService.putSubscription(11,modSubscription);
		Double val = 21.9;
		Assert.assertEquals(val, modSubscription.getMonthlyPrice());
	}
	
	private Subscription defaultSubscription() {
		Subscription subscriptionModel = new Subscription();
		subscriptionModel.setId(11);
		subscriptionModel.setName("Subscription-Mobile");
		subscriptionModel.setMonthlyPrice(111.9);
		subscriptionModel.setLastUpdate("7/4/2019 12:28");
		return subscriptionModel;
	}

}
