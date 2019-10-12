package com.intuit.tms.services;

import com.intuit.tms.entities.Workflow;

import java.util.List;

import com.intuit.tms.dto.WorkflowDTO;

public interface WorkflowService {
	Workflow getWorkflowByTitle(String title);

	Workflow getWorkflowById(Long id);

	Workflow saveWorkflow(WorkflowDTO workflowDTO);

	List<Workflow> getAllWorkflows();

	void deleteWorkflow(Long id);

	Workflow updateWorkflow(WorkflowDTO workflowDTO, Long workflowId);
}