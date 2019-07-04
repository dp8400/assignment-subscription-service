package com.tele2.subscription.api;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tele2.subscription.model.Subscription;
import com.tele2.subscription.service.SubscriptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;
/**
 * Controller for the application
 *
 */
@RestController
@Api("subscription-service")
public class SubscriptionServiceController {
	@Autowired
	private SubscriptionService subscriptionService;

	/**
	* 
	*/
	@GetMapping(value = "/api/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get a list of subscriptions", tags = "GET /api/subscriptions", response = Subscription.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })

	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved subscriptions list"),
			@ApiResponse(code = 403, message = "The resource you were trying to reach is protected.You do not have permission to access it"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<List<Subscription>> getSubscriptions(
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		List<Subscription> data = subscriptionService.getSubscriptions();
		return new ResponseEntity<List<Subscription>>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/api/subscriptions/{subscriptionId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "retrieve one subscription ", tags = "GET /api/subscriptions/{subscriptionId}", response = Subscription.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses({ @ApiResponse(code = 200, message = "Successfully retrieved subscription"),
			@ApiResponse(code = 403, message = "The resource you were trying to reach is protected.You do not have permission to access it"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<Subscription> getSubscription(@PathVariable("subscriptionId") int subscriptionId,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		Subscription data = subscriptionService.getSubscription(subscriptionId);
		return new ResponseEntity<Subscription>(data, HttpStatus.OK);

	}

	@PostMapping(value = "/api/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Create a subscription", tags = "POST /api/subscriptions")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses({ @ApiResponse(code = 201, message = "Successfully created a subscription"),
			@ApiResponse(code = 403, message = "The resource you were trying to reach is protected.You do not have permission to access it") })
	public ResponseEntity<Subscription> postSubscriptions(@Valid @RequestBody Subscription subscription,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		Subscription data = subscriptionService.postSubscriptions(subscription);
		return new ResponseEntity<Subscription>(data, HttpStatus.CREATED);
	}

	@PutMapping(value = "/api/subscriptions/{subscriptionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update a subscription", tags = "PUT /api/subscriptions/{subscriptionId}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization", required = true, dataType = "string", paramType = "header") })
	@ApiResponses({ @ApiResponse(code = 204, message = "Successfully updated a subscription"),
			@ApiResponse(code = 403, message = "The resource you were trying to reach is protected.You do not have permission to access it"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<Integer> putSubscription(@Valid @RequestBody Subscription subscription,
			@PathVariable("subscriptionId") int subscriptionId,
			@ApiIgnore @RequestHeader(required = false) HttpHeaders headers,
			@ApiIgnore @PathVariable(required = false) Map<String, String> pathParams,
			@ApiIgnore @RequestParam(required = false) MultiValueMap<String, String> queryParams) {

		subscriptionService.putSubscription(subscriptionId, subscription);
		return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
	}
}
