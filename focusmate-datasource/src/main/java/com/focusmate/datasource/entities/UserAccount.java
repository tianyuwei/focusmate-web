package com.focusmate.datasource.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserAccount")
public class UserAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 287803035666971666L;

	@Id
	private Integer userId;

	// �˻����
	private Double accountBalance;

	// �������
	private Double lockedBalance;

	// ��ǰ��ֵ��¼
	private Integer chargeRecord;

	// ��ǰ���Ѽ�¼
	private Integer consumeRecord;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double getLockedBalance() {
		return lockedBalance;
	}

	public void setLockedBalance(Double lockedBalance) {
		this.lockedBalance = lockedBalance;
	}

	public Integer getChargeRecord() {
		return chargeRecord;
	}

	public void setChargeRecord(Integer chargeRecord) {
		this.chargeRecord = chargeRecord;
	}

	public Integer getConsumeRecord() {
		return consumeRecord;
	}

	public void setConsumeRecord(Integer consumeRecord) {
		this.consumeRecord = consumeRecord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result
				+ ((chargeRecord == null) ? 0 : chargeRecord.hashCode());
		result = prime * result
				+ ((consumeRecord == null) ? 0 : consumeRecord.hashCode());
		result = prime * result
				+ ((lockedBalance == null) ? 0 : lockedBalance.hashCode());
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
		UserAccount other = (UserAccount) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (chargeRecord == null) {
			if (other.chargeRecord != null)
				return false;
		} else if (!chargeRecord.equals(other.chargeRecord))
			return false;
		if (consumeRecord == null) {
			if (other.consumeRecord != null)
				return false;
		} else if (!consumeRecord.equals(other.consumeRecord))
			return false;
		if (lockedBalance == null) {
			if (other.lockedBalance != null)
				return false;
		} else if (!lockedBalance.equals(other.lockedBalance))
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
		return "UserAccount [userId=" + userId + ", accountBalance="
				+ accountBalance + ", lockedBalance=" + lockedBalance
				+ ", chargeRecord=" + chargeRecord + ", consumeRecord="
				+ consumeRecord + "]";
	}

}
