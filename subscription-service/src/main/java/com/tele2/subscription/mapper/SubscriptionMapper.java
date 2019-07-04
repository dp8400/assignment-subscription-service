package com.tele2.subscription.mapper;

import org.mapstruct.Mapper;

import com.tele2.subscription.entity.SubscriptionEntity;
import com.tele2.subscription.model.Subscription;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
	Subscription toSubscription(SubscriptionEntity entity);
	SubscriptionEntity toSubscriptionEntity(Subscription subscription);

}
