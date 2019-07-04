package com.tele2.subscription.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Subscription")
public class Subscription implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = true, dataType = "integer", position = 0, value = "", example = "1234")
	private Integer id;
	
	@JsonProperty("name")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = true, dataType = "string", position = 0, value = "", example = "john doe")
	private String name;
	
	@JsonProperty("monthlyPrice")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "number", position = 0, value = "", example = "100.12")
	private Double monthlyPrice;
	
	@JsonProperty("lastUpdate")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	private String lastUpdate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(Double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
