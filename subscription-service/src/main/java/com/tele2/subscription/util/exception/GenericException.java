package com.tele2.subscription.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Exception entity
 *
 */
@ApiModel(description = "Represents an exception of the system")
@JsonInclude(Include.NON_NULL)
public class GenericException {

	@ApiModelProperty(value = "The timestamp of the exception", required = true)
	private String timestamp;
	@ApiModelProperty(value = "The http status code associated with the exception", required = true)
	private Integer status;
	@ApiModelProperty(value = "The error associated with the exception", required = true)
	private String error;
	@ApiModelProperty(value = "The message associated with the exception", required = true)
	private String message;
	@ApiModelProperty(value = "The path on which the exception occured", required = true)
	private String path;

	public GenericException() {
	}

	public GenericException(String timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "GenericException [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + ", path=" + path + "]";
	}

}
