package com.intuit.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.intuit.tms.entities.WorkflowDetails;

public interface WorkflowDetailsRepository extends JpaRepository<WorkflowDetails, Long> {
	@Modifying
	@Query(value = "DELETE FROM workflow_details WHERE workflow_id=?1", nativeQuery = true)
	void deleteByWorkflowId(Long workflowId);
}