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

import com.intuit.tms.dto.ProjectDTO;
import com.intuit.tms.entities.Project;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.ProjectService;

import io.swagger.annotations.Api;

@RestController
@Api(description = "Project Management APIs", produces = "application/json", tags = { "A6" })
public class ProjectManagementRestController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("project")
	public ProjectDTO projectDTO() {
		return new ProjectDTO();
	}

	@PostMapping("/admin/api/v1/project")
	public ResponseEntity<Object> saveProject(@ModelAttribute("project") @Valid ProjectDTO projectDTO,
			BindingResult result) {

		Project existing = projectService.getProjectByName(projectDTO.getName());
		if (existing != null) {
			return new ResponseEntity<Object>("There is already exist a project with name as " + projectDTO.getName(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			projectService.saveProject(projectDTO);
			return new ResponseEntity<Object>("You have successfully added a project", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Project cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/api/v1/projects")
	public ResponseEntity<Object> getProjects() {

		try {
			List<Project> projects = projectService.getAllProjects();
			return new ResponseEntity<Object>(projects, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Cannot get the projects, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin/api/v1/project/{projectId}")
	public ResponseEntity<Object> updateProject(@PathVariable(name = "projectId") Long projectId,
			@ModelAttribute("project") @Valid ProjectDTO projectDTO, BindingResult result) {

		Project existing = projectService.getProjectById(projectId);
		if (existing == null) {
			return new ResponseEntity<Object>("The project with project id as " + projectId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			projectService.updateProject(projectDTO, projectId);
			return new ResponseEntity<Object>("You have successfully updated a project with project id " + projectId,
					HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Project cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/admin/api/v1/project/{projectId}")
	public ResponseEntity<Object> deleteProject(@PathVariable(name = "projectId") Long projectId) {

		Project existing = projectService.getProjectById(projectId);
		if (existing == null) {
			return new ResponseEntity<Object>("The project with project id as " + projectId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			projectService.deleteProject(projectId);
			return new ResponseEntity<Object>("Project successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Project cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
