package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IntegralRecord")
public class IntegralRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7376847345301321854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer autoKeyValue;

	private Integer userId;

	private Date signDate;

	private Date signTime;

	private String integral;

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

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autoKeyValue == null) ? 0 : autoKeyValue.hashCode());
		result = prime * result
				+ ((integral == null) ? 0 : integral.hashCode());
		result = prime * result
				+ ((signDate == null) ? 0 : signDate.hashCode());
		result = prime * result
				+ ((signTime == null) ? 0 : signTime.hashCode());
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
		IntegralRecord other = (IntegralRecord) obj;
		if (autoKeyValue == null) {
			if (other.autoKeyValue != null)
				return false;
		} else if (!autoKeyValue.equals(other.autoKeyValue))
			return false;
		if (integral == null) {
			if (other.integral != null)
				return false;
		} else if (!integral.equals(other.integral))
			return false;
		if (signDate == null) {
			if (other.signDate != null)
				return false;
		} else if (!signDate.equals(other.signDate))
			return false;
		if (signTime == null) {
			if (other.signTime != null)
				return false;
		} else if (!signTime.equals(other.signTime))
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
		return "IntegralRecord [autoKeyValue=" + autoKeyValue + ", userId="
				+ userId + ", signDate=" + signDate + ", signTime=" + signTime
				+ ", integral=" + integral + "]";
	}

}
