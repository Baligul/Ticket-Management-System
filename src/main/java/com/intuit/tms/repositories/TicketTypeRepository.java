package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
	Optional<TicketType> findByTitle(String title);
}
