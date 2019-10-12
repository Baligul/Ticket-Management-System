package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.Workflow;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.WorkflowRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.WorkflowService;
import com.intuit.tms.dto.WorkflowDTO;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	WorkflowRepository workflowRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Override
	public Workflow getWorkflowByTitle(String title) {
		Optional<Workflow> workflow = workflowRepository.findByTitle(title);
		if (workflow.isPresent()) {
			return workflow.get();
		}
		return null;
	}

	@Override
	public Workflow getWorkflowById(Long workflowId) {
		Optional<Workflow> workflow = workflowRepository.findById(workflowId);
		if (workflow.isPresent()) {
			return workflow.get();
		}
		return null;
	}

	@Override
	public Workflow saveWorkflow(WorkflowDTO workflowDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long createdBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Workflow workflow = new Workflow();
		workflow.setTitle(workflowDTO.getTitle());
		workflow.setCreatedBy(createdBy);
		return workflowRepository.save(workflow);
	}

	@Override
	public List<Workflow> getAllWorkflows() {
		return workflowRepository.findAll();
	}

	@Override
	public void deleteWorkflow(Long workflowId) {
		workflowRepository.deleteById(workflowId);
	}

	@Override
	public Workflow updateWorkflow(WorkflowDTO workflowDTO, Long workflowId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long updatedBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Workflow workflow = new Workflow();
		workflow.setId(workflowId);
		workflow.setTitle(workflowDTO.getTitle());
		workflow.setUpdatedBy(updatedBy);
		workflow.setUpdatedOn(LocalDateTime.now());
		return workflowRepository.save(workflow);
	}
}
