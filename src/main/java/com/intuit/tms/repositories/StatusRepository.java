package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {
	Optional<Status> findByStatus(String strStatus);

	void deleteByStatus(String status);
}
