package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.TicketType;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, String> {
	Optional<TicketType> findByTicketType(String ticketType);

	void deleteByTicketType(String ticketType);
}
