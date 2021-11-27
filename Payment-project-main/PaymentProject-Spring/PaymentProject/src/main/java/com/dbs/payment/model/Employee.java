package com.dbs.payment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	private int employeeid;
	
	private String employeename;
	
	private String employeepassword;
	
	
	public Employee() {
		
	}


	public Employee(int employeeid, String employeename, String employeepassword) {
		super();
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.employeepassword = employeepassword;
	}


	public int getEmployeeid() {
		return employeeid;
	}


	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}


	public String getEmployeename() {
		return employeename;
	}


	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}


	public String getEmployeepassword() {
		return employeepassword;
	}


	public void setEmployeepassword(String employeepassword) {
		this.employeepassword = employeepassword;
	}


	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeename=" + employeename + ", employeepassword="
				+ employeepassword + "]";
	}
	
	
	
	
}
