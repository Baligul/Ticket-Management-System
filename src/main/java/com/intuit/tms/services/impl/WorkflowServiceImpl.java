package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.tms.entities.Workflow;
import com.intuit.tms.entities.WorkflowDetails;
import com.intuit.tms.entities.WorkflowDetailsId;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.WorkflowDetailsRepository;
import com.intuit.tms.repositories.WorkflowRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.WorkflowService;
import com.intuit.tms.dto.WorkflowDTO;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	WorkflowRepository workflowRepository;

	@Autowired
	WorkflowDetailsRepository workflowDetailsRepository;

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

		// First save workflow in workflow table
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long createdBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Workflow workflow = new Workflow();
		workflow.setTitle(workflowDTO.getTitle());
		workflow.setCreatedBy(createdBy);
		Workflow savedWorkflow = workflowRepository.save(workflow);

		/* Create object of AtomicInteger with initial value `0` */
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// Then save workflow details in workflow_details table
		workflowDTO.getStatuses().parallelStream().forEachOrdered(status -> {
			atomicInteger.getAndIncrement();
			workflowDetailsRepository.save(
					new WorkflowDetails(new WorkflowDetailsId(savedWorkflow.getId(), status), atomicInteger.get()));
		});

		return savedWorkflow;
	}

	@Override
	public List<Workflow> getAllWorkflows() {
		return workflowRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteWorkflow(Long workflowId) {
		// Delete all workflow details from workflow_details table
		workflowDetailsRepository.deleteByWorkflowId(workflowId);

		// Delete workflow from workflow table
		workflowRepository.deleteById(workflowId);
	}

	@Transactional
	@Override
	public Workflow updateWorkflow(WorkflowDTO workflowDTO, Long workflowId) {
		// Update workflow title in workflow table
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long updatedBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Workflow workflow = new Workflow();
		workflow.setId(workflowId);
		workflow.setTitle(workflowDTO.getTitle());
		workflow.setUpdatedBy(updatedBy);
		workflow.setUpdatedOn(LocalDateTime.now());

		/* Create object of AtomicInteger with initial value `0` */
		AtomicInteger atomicInteger = new AtomicInteger(0);

		// Delete all workflow details from workflow_details table
		workflowDetailsRepository.deleteByWorkflowId(workflowId);

		// Then save workflow details in workflow_details table
		workflowDTO.getStatuses().parallelStream().forEachOrdered(status -> {
			atomicInteger.getAndIncrement();
			workflowDetailsRepository
					.save(new WorkflowDetails(new WorkflowDetailsId(workflowId, status), atomicInteger.get()));
		});
		return workflowRepository.save(workflow);
	}
}
