package com.tele2.subscription.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SUBSCRIPTION")
@SequenceGenerator(name = "subscription_generator", initialValue = 3, allocationSize = 1)
public class SubscriptionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_generator")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "MONTHLY_PRICE")
	private Double monthlyPrice;

	@Column(name = "LAST_UPDATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	public SubscriptionEntity(Integer id, String name, Double monthlyPrice, Date lastUpdate) {
		super();
		this.id = id;
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.monthlyPrice = monthlyPrice;
	}

	public SubscriptionEntity() {

	}

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Double getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(Double monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

}
