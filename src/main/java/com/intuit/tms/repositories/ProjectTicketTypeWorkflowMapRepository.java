package com.intuit.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.intuit.tms.entities.ProjectTicketTypeWorkflowMap;

public interface ProjectTicketTypeWorkflowMapRepository extends JpaRepository<ProjectTicketTypeWorkflowMap, Long> {
	@Modifying(flushAutomatically = true)
	@Query(value = "DELETE FROM project_ticket_type_workflow_map WHERE project_id=?1", nativeQuery = true)
	void deleteByProjectId(Long projectId);
}