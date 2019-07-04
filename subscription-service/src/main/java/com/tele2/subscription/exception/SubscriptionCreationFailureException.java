package com.tele2.subscription.exception;

public class SubscriptionCreationFailureException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_FORMAT = "Failed to create a subscription";

	public SubscriptionCreationFailureException() {
		super(String.format(MESSAGE_FORMAT));
	}

	public SubscriptionCreationFailureException(Throwable throwable, boolean arg2, boolean arg3) {
		super(String.format(MESSAGE_FORMAT), throwable, arg2, arg3);
	}

	public SubscriptionCreationFailureException(Throwable throwable) {
		super(String.format(MESSAGE_FORMAT), throwable);
	}

}
