package com.dbs.payment.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.payment.model.Message;

public interface MessageRepository extends CrudRepository<Message, String> {

}
