package com.tele2.subscription.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tele2.subscription.api.SubscriptionServiceController;
import com.tele2.subscription.model.Subscription;
import com.tele2.subscription.service.SubscriptionService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(SubscriptionServiceController.class)
public class SubscriptionServiceControllerTest {
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private SubscriptionService subscriptionService;
	private Subscription subscription;

	@Before
	public void setUp() throws Exception {
		subscription = new Subscription();
		subscription.setId(11);
		subscription.setName("Subscription-Mobile");
		subscription.setMonthlyPrice(111.9);
		subscription.setLastUpdate("7/4/2019 12:20");

	}

	@Test
	@WithMockUser
	public void testGetSubscriptions() throws Exception {
		Mockito.when(subscriptionService.getSubscriptions()).thenReturn(Arrays.asList(subscription));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/subscriptions").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@WithMockUser
	public void testGetSubscription() throws Exception {
		Mockito.when(subscriptionService.getSubscription(Mockito.any(int.class))).thenReturn(subscription);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/subscriptions/11").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andDo(MockMvcResultHandlers.print());
	}

	private Subscription updatedSubscription() {
		Subscription subscription = new Subscription();
		subscription.setId(11);
		subscription.setName("Subscription-Mobile");
		subscription.setMonthlyPrice(11.9);
		subscription.setLastUpdate("7/4/2019 12:24");
		return subscription;
	}

	@Test
	@WithMockUser
	public void testPutSubscription() throws Exception {
		Mockito.doNothing().when(subscriptionService).putSubscription(Mockito.anyInt(), Mockito.any(Subscription.class));

		String requestBody = mapper.writeValueAsString(updatedSubscription());
		mockMvc.perform(MockMvcRequestBuilders.put("/api/subscriptions/11").content(requestBody)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NO_CONTENT.value()))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@WithMockUser(username="admin",roles={"ADMIN"})
	public void testPostSubscriptions() throws Exception {
		Mockito.when(subscriptionService.postSubscriptions(Mockito.any(Subscription.class))).thenReturn(subscription);
		java.lang.String requestBody = mapper.writeValueAsString(subscription);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/subscriptions").content(requestBody)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
				.andDo(MockMvcResultHandlers.print());
	}

}
