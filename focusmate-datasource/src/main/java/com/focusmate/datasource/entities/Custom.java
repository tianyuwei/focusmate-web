package com.focusmate.datasource.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "custom")
public class Custom implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = 5927093232887817700L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private UserInformation userInformation;

	private Integer cash;

	private Integer frozenCash;

	private Integer rechargeSum;

	private Integer consumeSum;

	private Integer withdrawSum;

	private Date createDate;

	private Date visitDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInformation getInfo() {
		return userInformation;
	}

	public void setInfo(UserInformation info) {
		this.userInformation = info;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public Integer getFrozenCash() {
		return frozenCash;
	}

	public void setFrozenCash(Integer frozenCash) {
		this.frozenCash = frozenCash;
	}

	public Integer getRechargeSum() {
		return rechargeSum;
	}

	public void setRechargeSum(Integer rechargeSum) {
		this.rechargeSum = rechargeSum;
	}

	public Integer getConsumeSum() {
		return consumeSum;
	}

	public void setConsumeSum(Integer consumeSum) {
		this.consumeSum = consumeSum;
	}

	public Integer getWithdrawSum() {
		return withdrawSum;
	}

	public void setWithdrawSum(Integer withdrawSum) {
		this.withdrawSum = withdrawSum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
