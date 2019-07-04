package com.tele2.subscription.service;

import java.util.List;

import com.tele2.subscription.model.Subscription;

public interface SubscriptionService {
	
	List<Subscription> getSubscriptions();

	Subscription getSubscription(int id);

	void putSubscription(int id, Subscription subscription);

	Subscription postSubscriptions(Subscription subscription);

}
