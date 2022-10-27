package com.virtusa.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Statement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

	private long accountno;
	private double balance;
	private double availbal;
	private String type;
	public long getAccountno() {
		return accountno;
	}
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAvailbal() {
		return availbal;
	}
	public void setAvailbal(double availbal) {
		this.availbal = availbal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Statement [accountno=" + accountno + ", balance=" + balance + ", availbal=" + availbal + ", type="
				+ type + "]";
	}
	

}
