package com.tele2.subscription.exception;

public class SubscriptionUpdationFailureException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_FORMAT = "Failed to update subscription";

	public SubscriptionUpdationFailureException() {
		super(String.format(MESSAGE_FORMAT));
	}

	public SubscriptionUpdationFailureException(Throwable throwable, boolean arg2, boolean arg3) {
		super(String.format(MESSAGE_FORMAT), throwable, arg2, arg3);
	}

	public SubscriptionUpdationFailureException(Throwable throwable) {
		super(String.format(MESSAGE_FORMAT), throwable);
	}

}
