package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.tms.entities.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
	Optional<Workflow> findByTitle(String title);
}
