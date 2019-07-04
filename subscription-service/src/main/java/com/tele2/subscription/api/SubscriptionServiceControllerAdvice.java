package com.tele2.subscription.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tele2.subscription.exception.SubscriptionNotFoundException;
import com.tele2.subscription.util.exception.GenericException;
/**
 * Exception handler for the app
 *
 */
@ControllerAdvice
public class SubscriptionServiceControllerAdvice extends ResponseEntityExceptionHandler {
	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<GenericException> handleException(Exception e) {
		log.error(e.getMessage(), e);
		GenericException exception = createGenericException(e,HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<GenericException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SubscriptionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<GenericException> handleSubscriptionNotFoundException(SubscriptionNotFoundException e) {
		log.error(e.getMessage());
		GenericException exception = createGenericException(e,HttpStatus.NOT_FOUND);
		return new ResponseEntity<GenericException>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<GenericException> handleAccessDeniedException(AccessDeniedException e) {
		log.error(e.getMessage());
		GenericException exception = createGenericException(e,HttpStatus.FORBIDDEN);
		return new ResponseEntity<GenericException>(exception, HttpStatus.FORBIDDEN);
	}
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	log.error(e.getMessage(), e);
	GenericException exception = createGenericException(e,HttpStatus.BAD_REQUEST);
	String errorMsg = e.getBindingResult().getFieldErrors().stream()
		.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(e.getMessage());
	exception.setMessage(errorMsg);
	return new ResponseEntity<Object>(exception, HttpStatus.BAD_REQUEST);
    }

	private GenericException createGenericException(Exception e,HttpStatus status) {
		GenericException exception = new GenericException();
		exception.setTimestamp(new SimpleDateFormat("EEEEE MMMMM yyyy-MM-dd HH:mm:ss.SSSZ").format(new Date()));
		exception.setStatus(status.value());
		exception.setError(status.getReasonPhrase());
		exception.setMessage(e.getLocalizedMessage());
		return exception;
	}
}
