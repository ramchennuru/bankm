package com.virtusa.bank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Bank {
	@Id
	private Long accountno;
	private double balance;
	private String uname;
	private long debitcardno;
	private Integer debitPin;
	private Integer creditcardPin;
	private long creditcardNo;
	private double creditcardBal;
	//private long phone;
	public Long getAccountno() {
		return accountno;
	}
	public void setAccountno(Long accountno) {
		this.accountno = accountno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public long getDebitcardno() {
		return debitcardno;
	}
	public void setDebitcardno(long debitcardno) {
		this.debitcardno = debitcardno;
	}
	public Integer getDebitPin() {
		return debitPin;
	}
	public void setDebitPin(Integer debitPin) {
		this.debitPin = debitPin;
	}
	public Integer getCreditcardPin() {
		return creditcardPin;
	}
	public void setCreditcardPin(Integer creditcardPin) {
		this.creditcardPin = creditcardPin;
	}
	public long getCreditcardNo() {
		return creditcardNo;
	}
	public void setCreditcardNo(long creditcardNo) {
		this.creditcardNo = creditcardNo;
	}
	public double getCreditcardBal() {
		return creditcardBal;
	}
	public void setCreditcardBal(double creditcardBal) {
		this.creditcardBal = creditcardBal;
	}
	public Bank() {
		super();
	}
	@Override
	public String toString() {
		return "Bank [accountno=" + accountno + ", balance=" + balance + ", uname=" + uname + ", debitcardno="
				+ debitcardno + ", debitPin=" + debitPin + ", creditcardPin=" + creditcardPin + ", creditcardNo="
				+ creditcardNo + ", creditcardBal=" + creditcardBal + "]";
	}

	public Bank(Long accountno, double balance, String uname, long debitcardno, Integer debitPin, Integer creditcardPin, long creditcardNo, double creditcardBal) {
		this.accountno = accountno;
		this.balance = balance;
		this.uname = uname;
		this.debitcardno = debitcardno;
		this.debitPin = debitPin;
		this.creditcardPin = creditcardPin;
		this.creditcardNo = creditcardNo;
		this.creditcardBal = creditcardBal;
	}

}
