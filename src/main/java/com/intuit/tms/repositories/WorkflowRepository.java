package com.intuit.tms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intuit.tms.entities.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
	Optional<Workflow> findByTitle(String title);
}
