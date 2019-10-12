package com.intuit.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
