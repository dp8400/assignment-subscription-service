package com.tele2.subscription.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tele2.subscription.entity.SubscriptionEntity;
import com.tele2.subscription.service.SubscriptionService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SubscriptionRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @MockBean
	private SubscriptionService subscriptionService;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testFindById() {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setName("Subscription-Mobile");
		subscriptionEntity.setMonthlyPrice(111.9);
		subscriptionEntity.setLastUpdate(new Date());
		
		subscriptionEntity = entityManager.persist(subscriptionEntity);
	    entityManager.flush();
	    // when
	    Optional<SubscriptionEntity> optional = subscriptionRepository.findById(subscriptionEntity.getId());
	    SubscriptionEntity res = optional.get();
	 
	    Assert.assertEquals(res.getId(), subscriptionEntity.getId());
	}
	@Test
	public void testFindAll() {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
		subscriptionEntity.setName("Subscription-T");
		subscriptionEntity.setMonthlyPrice(1.9);
		subscriptionEntity.setLastUpdate(new Date());
		
		subscriptionEntity = entityManager.persist(subscriptionEntity);
	    entityManager.flush();
	    // when
	    List<SubscriptionEntity> all = subscriptionRepository.findAll();
	 
	    Assert.assertEquals(all.size(), 3);
	}
}
