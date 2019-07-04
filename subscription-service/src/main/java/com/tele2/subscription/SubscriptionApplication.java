package com.tele2.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.event.EventListener;

import com.tele2.subscription.service.SubscriptionService;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@EnableCaching
public class SubscriptionApplication {
	@Autowired
	private SubscriptionService subscriptionService;

	@EventListener(ApplicationReadyEvent.class)
	public void populateCache() {
		subscriptionService.getSubscriptions();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SubscriptionApplication.class, args);
	}
}
