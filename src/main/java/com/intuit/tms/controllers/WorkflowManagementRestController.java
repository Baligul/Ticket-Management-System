package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.WorkflowDTO;
import com.intuit.tms.entities.Workflow;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.WorkflowService;

import io.swagger.annotations.Api;

@RestController
@Api(description = "Workflow Management APIs", produces = "application/json", tags = { "A4" })
public class WorkflowManagementRestController {

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("workflow")
	public WorkflowDTO workflowDTO() {
		return new WorkflowDTO();
	}

	@PostMapping("/admin/api/v1/workflow")
	public ResponseEntity<Object> saveWorkflow(@ModelAttribute("workflow") @Valid WorkflowDTO workflowDTO,
			BindingResult result) {

		Workflow existing = workflowService.getWorkflowByTitle(workflowDTO.getTitle());
		if (existing != null) {
			return new ResponseEntity<Object>(
					"There is already exist a workflow with title as " + workflowDTO.getTitle(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			workflowService.saveWorkflow(workflowDTO);
			return new ResponseEntity<Object>("You have successfully added a workflow", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Workflow cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/api/v1/workflows")
	public ResponseEntity<Object> getWorkflows() {

		try {
			List<Workflow> workflows = workflowService.getAllWorkflows();
			return new ResponseEntity<Object>(workflows, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Workflows cannot be fetched, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin/api/v1/workflow/{workflowId}")
	public ResponseEntity<Object> updateWorkflow(@PathVariable(name = "workflowId") Long workflowId,
			@ModelAttribute("workflow") @Valid WorkflowDTO workflowDTO, BindingResult result) {

		Workflow existing = workflowService.getWorkflowById(workflowId);
		if (existing == null) {
			return new ResponseEntity<Object>("The workflow with workflow id as " + workflowId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			workflowService.updateWorkflow(workflowDTO, workflowId);
			return new ResponseEntity<Object>("You have successfully updated a workflow with workflow id " + workflowId,
					HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Workflow cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/admin/api/v1/workflow/{workflowId}")
	public ResponseEntity<Object> deleteWorkflow(@PathVariable(name = "workflowId") Long workflowId) {

		Workflow existing = workflowService.getWorkflowById(workflowId);
		if (existing == null) {
			return new ResponseEntity<Object>("The workflow with workflow id as " + workflowId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			workflowService.deleteWorkflow(workflowId);
			return new ResponseEntity<Object>("Workflow successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Workflow cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
