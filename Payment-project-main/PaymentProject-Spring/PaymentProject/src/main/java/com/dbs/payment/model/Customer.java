package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column( columnDefinition = "char(14)")
	private String customerid;
	
	private String accountholdername;
	
	private byte overdraftflag;
	
	private Double clearbalance;
	
	private String customeraddress;
	
	private String customercity;
	
	@Column(columnDefinition = "char(1)")
	private String customertype;
	
	@OneToOne
	@JoinColumn(name="bic")
	private Bank bank;
	
	
	public Customer() {
		
	}


	public Customer(String customerid, String accountholdername, byte overdraftflag, Double clearbalance,
			String customeraddress, String customercity, String customertype, Bank bank) {
		super();
		this.customerid = customerid;
		this.accountholdername = accountholdername;
		this.overdraftflag = overdraftflag;
		this.clearbalance = clearbalance;
		this.customeraddress = customeraddress;
		this.customercity = customercity;
		this.customertype = customertype;
		this.bank = bank;
	}


	public String getCustomerid() {
		return customerid;
	}


	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}


	public String getAccountholdername() {
		return accountholdername;
	}


	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}


	public byte getOverdraftflag() {
		return overdraftflag;
	}


	public void setOverdraftflag(byte overdraftflag) {
		this.overdraftflag = overdraftflag;
	}


	public Double getClearbalance() {
		return clearbalance;
	}


	public void setClearbalance(Double clearbalance) {
		this.clearbalance = clearbalance;
	}


	public String getCustomeraddress() {
		return customeraddress;
	}


	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}


	public String getCustomercity() {
		return customercity;
	}


	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}


	public String getCustomertype() {
		return customertype;
	}


	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}


	public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
	}


	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", accountholdername=" + accountholdername + ", overdraftflag="
				+ overdraftflag + ", clearbalance=" + clearbalance + ", customeraddress=" + customeraddress
				+ ", customercity=" + customercity + ", customertype=" + customertype + ", bank=" + bank + "]";
	}
	
	


	
	
	
	
}


