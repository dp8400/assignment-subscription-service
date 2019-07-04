package com.tele2.subscription.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tele2.subscription.entity.SubscriptionEntity;

public interface SubscriptionRepository
		extends JpaRepository<SubscriptionEntity, Serializable>, JpaSpecificationExecutor<SubscriptionEntity> {

}
