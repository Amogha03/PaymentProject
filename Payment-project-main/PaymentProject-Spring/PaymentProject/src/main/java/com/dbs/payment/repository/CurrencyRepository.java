package com.dbs.payment.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, String>{

}
