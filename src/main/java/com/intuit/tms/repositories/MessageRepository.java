package com.intuit.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
