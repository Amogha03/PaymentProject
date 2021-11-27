package com.dbs.payment.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
