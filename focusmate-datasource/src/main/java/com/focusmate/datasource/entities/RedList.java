package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RedList")
public class RedList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8912264083538318718L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer autoKeyValue;

	private Integer redIssuer;

	private Double redAmount;

	private Integer redNumber;

	private Date effectiveTime;

	private Date endTime;

	private String logoAddress;

	public Integer getAutoKeyValue() {
		return autoKeyValue;
	}

	public void setAutoKeyValue(Integer autoKeyValue) {
		this.autoKeyValue = autoKeyValue;
	}

	public Integer getRedIssuer() {
		return redIssuer;
	}

	public void setRedIssuer(Integer redIssuer) {
		this.redIssuer = redIssuer;
	}

	public Double getRedAmount() {
		return redAmount;
	}

	public void setRedAmount(Double redAmount) {
		this.redAmount = redAmount;
	}

	public Integer getRedNumber() {
		return redNumber;
	}

	public void setRedNumber(Integer redNumber) {
		this.redNumber = redNumber;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLogoAddress() {
		return logoAddress;
	}

	public void setLogoAddress(String logoAddress) {
		this.logoAddress = logoAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((autoKeyValue == null) ? 0 : autoKeyValue.hashCode());
		result = prime * result
				+ ((effectiveTime == null) ? 0 : effectiveTime.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result
				+ ((logoAddress == null) ? 0 : logoAddress.hashCode());
		result = prime * result
				+ ((redAmount == null) ? 0 : redAmount.hashCode());
		result = prime * result
				+ ((redIssuer == null) ? 0 : redIssuer.hashCode());
		result = prime * result
				+ ((redNumber == null) ? 0 : redNumber.hashCode());
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
		RedList other = (RedList) obj;
		if (autoKeyValue == null) {
			if (other.autoKeyValue != null)
				return false;
		} else if (!autoKeyValue.equals(other.autoKeyValue))
			return false;
		if (effectiveTime == null) {
			if (other.effectiveTime != null)
				return false;
		} else if (!effectiveTime.equals(other.effectiveTime))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (logoAddress == null) {
			if (other.logoAddress != null)
				return false;
		} else if (!logoAddress.equals(other.logoAddress))
			return false;
		if (redAmount == null) {
			if (other.redAmount != null)
				return false;
		} else if (!redAmount.equals(other.redAmount))
			return false;
		if (redIssuer == null) {
			if (other.redIssuer != null)
				return false;
		} else if (!redIssuer.equals(other.redIssuer))
			return false;
		if (redNumber == null) {
			if (other.redNumber != null)
				return false;
		} else if (!redNumber.equals(other.redNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RedList [autoKeyValue=" + autoKeyValue + ", redIssuer="
				+ redIssuer + ", redAmount=" + redAmount + ", redNumber="
				+ redNumber + ", effectiveTime=" + effectiveTime + ", endTime="
				+ endTime + ", logoAddress=" + logoAddress + "]";
	}
	
	

}
