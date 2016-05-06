package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ChargeRecord")
public class ChargeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6631834924900434479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer chargeNumber;

	private Date chargeStartDate;

	private Date chargeEndDate;

	private Integer userId;

	private Integer chargeAmount;

	private String chargeId;

	public enum ChargeRecordState {
		Prepare, Charging, Checking, Ready, Fail, None
	}

	private ChargeRecordState state;

	private String token;

	public Integer getChargeNumber() {
		return chargeNumber;
	}

	public void setChargeNumber(Integer chargeNumber) {
		this.chargeNumber = chargeNumber;
	}

	public Date getChargeStartDate() {
		return chargeStartDate;
	}

	public void setChargeStartDate(Date chargeStartDate) {
		this.chargeStartDate = chargeStartDate;
	}

	public Date getChargeEndDate() {
		return chargeEndDate;
	}

	public void setChargeEndDate(Date chargeEndDate) {
		this.chargeEndDate = chargeEndDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Integer chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public ChargeRecordState getState() {
		return state;
	}

	public void setState(ChargeRecordState state) {
		this.state = state;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getChargeId() {
		return chargeId;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

}
