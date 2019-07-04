package com.tele2.subscription.exception;

public class SubscriptionNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_FORMAT = "Subscription not found with id - %s";

	public SubscriptionNotFoundException(String id) {
		super(String.format(MESSAGE_FORMAT, id));
	}

	public SubscriptionNotFoundException(String id, Throwable throwable, boolean arg2, boolean arg3) {
		super(String.format(MESSAGE_FORMAT, id), throwable, arg2, arg3);
	}

	public SubscriptionNotFoundException(String id, Throwable throwable) {
		super(String.format(MESSAGE_FORMAT, id), throwable);
	}

	public SubscriptionNotFoundException(Throwable throwable) {
		super(throwable);
	}

}
