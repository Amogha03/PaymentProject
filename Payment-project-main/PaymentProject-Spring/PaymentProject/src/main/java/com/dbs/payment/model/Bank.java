package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="bank")
public class Bank {

	@Id
	@Column(name="bic", columnDefinition = "char(11)")
	private String bic;
	
	@Column(name="bankname")
	@NotNull
	private String bankname;
	
	public Bank() {
		
	}

	public Bank(String bic, String bankname) {
		super();
		this.bic = bic;
		this.bankname = bankname;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Override
	public String toString() {
		return "Bank [bic=" + bic + ", bankname=" + bankname + "]";
	}

	
	
	
	
	
	
}
