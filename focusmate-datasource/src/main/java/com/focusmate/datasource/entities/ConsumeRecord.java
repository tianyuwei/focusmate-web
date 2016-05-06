package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ConsumeRecord")
public class ConsumeRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578098375652120075L;

	@Id
	private Integer consumeId;

	private Integer userId;

	private Integer siteId;

	private Date consumeDate;

	private Date consumeTime;

	private Double consumeAmount;

	public enum ConsumeRecordState {
		Prepare, Checking, Consuming, Ready
	}

	private ConsumeRecordState state;

	public Integer getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(Integer consumeId) {
		this.consumeId = consumeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Date getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}

	public Date getConsumeTime() {
		return consumeTime;
	}

	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	public ConsumeRecordState getState() {
		return state;
	}

	public void setState(ConsumeRecordState state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumeAmount == null) ? 0 : consumeAmount.hashCode());
		result = prime * result
				+ ((consumeDate == null) ? 0 : consumeDate.hashCode());
		result = prime * result
				+ ((consumeId == null) ? 0 : consumeId.hashCode());
		result = prime * result
				+ ((consumeTime == null) ? 0 : consumeTime.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsumeRecord other = (ConsumeRecord) obj;
		if (consumeAmount == null) {
			if (other.consumeAmount != null)
				return false;
		} else if (!consumeAmount.equals(other.consumeAmount))
			return false;
		if (consumeDate == null) {
			if (other.consumeDate != null)
				return false;
		} else if (!consumeDate.equals(other.consumeDate))
			return false;
		if (consumeId == null) {
			if (other.consumeId != null)
				return false;
		} else if (!consumeId.equals(other.consumeId))
			return false;
		if (consumeTime == null) {
			if (other.consumeTime != null)
				return false;
		} else if (!consumeTime.equals(other.consumeTime))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
			return false;
		if (state != other.state)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsumeRecord [consumeId=" + consumeId + ", userId=" + userId
				+ ", siteId=" + siteId + ", consumeDate=" + consumeDate
				+ ", consumeTime=" + consumeTime + ", consumeAmount="
				+ consumeAmount + ", state=" + state + "]";
	}

}
