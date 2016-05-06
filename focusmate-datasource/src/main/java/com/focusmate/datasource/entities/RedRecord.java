package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RedRecord")
public class RedRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 364101913994324499L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer autoKeyValue;

	private Integer userId;

	private Date getRedDate;

	private Date getRedTime;

	private Double redCashAmount;

	public Integer getAutoKeyValue() {
		return autoKeyValue;
	}

	public void setAutoKeyValue(Integer autoKeyValue) {
		this.autoKeyValue = autoKeyValue;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getGetRedDate() {
		return getRedDate;
	}

	public void setGetRedDate(Date getRedDate) {
		this.getRedDate = getRedDate;
	}

	public Date getGetRedTime() {
		return getRedTime;
	}

	public void setGetRedTime(Date getRedTime) {
		this.getRedTime = getRedTime;
	}

	public Double getRedCashAmount() {
		return redCashAmount;
	}

	public void setRedCashAmount(Double redCashAmount) {
		this.redCashAmount = redCashAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autoKeyValue == null) ? 0 : autoKeyValue.hashCode());
		result = prime * result
				+ ((getRedDate == null) ? 0 : getRedDate.hashCode());
		result = prime * result
				+ ((getRedTime == null) ? 0 : getRedTime.hashCode());
		result = prime * result
				+ ((redCashAmount == null) ? 0 : redCashAmount.hashCode());
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
		RedRecord other = (RedRecord) obj;
		if (autoKeyValue == null) {
			if (other.autoKeyValue != null)
				return false;
		} else if (!autoKeyValue.equals(other.autoKeyValue))
			return false;
		if (getRedDate == null) {
			if (other.getRedDate != null)
				return false;
		} else if (!getRedDate.equals(other.getRedDate))
			return false;
		if (getRedTime == null) {
			if (other.getRedTime != null)
				return false;
		} else if (!getRedTime.equals(other.getRedTime))
			return false;
		if (redCashAmount == null) {
			if (other.redCashAmount != null)
				return false;
		} else if (!redCashAmount.equals(other.redCashAmount))
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
		return "RedRecord [autoKeyValue=" + autoKeyValue + ", userId=" + userId
				+ ", getRedDate=" + getRedDate + ", getRedTime=" + getRedTime
				+ ", redCashAmount=" + redCashAmount + "]";
	}

}
