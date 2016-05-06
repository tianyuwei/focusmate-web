package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SiteRevenue")
public class SiteRevenue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6508289885629780335L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer siteId;

	private Date incomeDate;

	private Date incomeTime;

	private Integer userId;

	private Double IncomeAmount;

	private String remark;

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public Date getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Date incomeTime) {
		this.incomeTime = incomeTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getIncomeAmount() {
		return IncomeAmount;
	}

	public void setIncomeAmount(Double incomeAmount) {
		IncomeAmount = incomeAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IncomeAmount == null) ? 0 : IncomeAmount.hashCode());
		result = prime * result
				+ ((incomeDate == null) ? 0 : incomeDate.hashCode());
		result = prime * result
				+ ((incomeTime == null) ? 0 : incomeTime.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((siteId == null) ? 0 : siteId.hashCode());
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
		SiteRevenue other = (SiteRevenue) obj;
		if (IncomeAmount == null) {
			if (other.IncomeAmount != null)
				return false;
		} else if (!IncomeAmount.equals(other.IncomeAmount))
			return false;
		if (incomeDate == null) {
			if (other.incomeDate != null)
				return false;
		} else if (!incomeDate.equals(other.incomeDate))
			return false;
		if (incomeTime == null) {
			if (other.incomeTime != null)
				return false;
		} else if (!incomeTime.equals(other.incomeTime))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (siteId == null) {
			if (other.siteId != null)
				return false;
		} else if (!siteId.equals(other.siteId))
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
		return "SiteRevenue [siteId=" + siteId + ", incomeDate=" + incomeDate
				+ ", incomeTime=" + incomeTime + ", userId=" + userId
				+ ", IncomeAmount=" + IncomeAmount + ", remark=" + remark + "]";
	}

}
