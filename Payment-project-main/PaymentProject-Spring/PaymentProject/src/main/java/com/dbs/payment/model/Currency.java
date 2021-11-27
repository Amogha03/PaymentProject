package com.dbs.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="currency")
public class Currency {

	@Id
	@Column(columnDefinition = "char(3)")
	private String currencycode;
	
	
	private String currencyname;
	
	private Double conversionrate;
	
	
	public Currency() {
		
	}


	public String getCurrencycode() {
		return currencycode;
	}


	public Currency(String currencycode, String currencyname, Double conversionrate) {
		super();
		this.currencycode = currencycode;
		this.currencyname = currencyname;
		this.conversionrate = conversionrate;
	}


	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}


	public String getCurrencyname() {
		return currencyname;
	}


	public void setCurrencyname(String currencyname) {
		this.currencyname = currencyname;
	}


	public Double getConversionrate() {
		return conversionrate;
	}


	public void setConversionrate(Double conversionrate) {
		this.conversionrate = conversionrate;
	}


	@Override
	public String toString() {
		return "Currency [currencycode=" + currencycode + ", currencyname=" + currencyname + ", conversionrate="
				+ conversionrate + "]";
	}
	
	
	
	
}
