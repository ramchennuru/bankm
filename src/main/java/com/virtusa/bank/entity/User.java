package com.virtusa.bank.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
@Entity
public class User {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adhar;
	private long id;
	private String name;
	private long phone;
	@Pattern(regexp = "^(?=.*[0-9])"+ "(?=.*[a-z])(?=.*[A-Z])"+ "(?=.*[@#$%^&+=])"+ "(?=\\S+$).{8,20}$", message = "pwd can be lower n highr nspecial n digits")
	private String pwd;
	
	private String role;
	@OneToOne(cascade=CascadeType.ALL)
	private Bank  ba;


    public long getAdhar() {
		return adhar;
	}
	public void setAdhar(long adhar) {
		this.adhar = adhar;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Bank getBa() {
		return ba;
	}
	public void setBa(Bank ba) {
		this.ba = ba;
	}
	@Override
	public String toString() {
		return "User [adhar=" + adhar + ", id=" + id + ", name=" + name + ", phone=" + phone + ", pwd=" + pwd
				+ ", role=" + role + ", ba=" + ba + "]";
	}
	public User(long adhar, long id, String name, long phone, String pwd, String role, Bank ba) {
		super();
		this.adhar = adhar;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.role = role;
		this.ba = ba;
	}
	public User() {
		super();
	}
	
	
	
}
