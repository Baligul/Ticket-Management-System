package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.tms.entities.Project;
import com.intuit.tms.entities.ProjectTicketTypeWorkflowMap;
import com.intuit.tms.entities.ProjectTicketTypeWorkflowMapId;
import com.intuit.tms.repositories.ProjectTeamMapRepository;
import com.intuit.tms.repositories.ProjectTicketTypeWorkflowMapRepository;
import com.intuit.tms.repositories.ProjectRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.ProjectService;
import com.intuit.tms.dto.ProjectDTO;
import com.intuit.tms.entities.Team;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	ProjectTeamMapRepository projectTeamMapRepository;

	@Autowired
	ProjectTicketTypeWorkflowMapRepository projectTicketTypeWorkflowMapRepository;

	@Override
	public Project getProjectByName(String name) {
		Optional<Project> project = projectRepository.findByName(name);
		if (project.isPresent()) {
			return project.get();
		}
		return null;
	}

	@Override
	public Project getProjectById(Long id) {
		Optional<Project> project = projectRepository.findById(id);
		if (project.isPresent()) {
			return project.get();
		}
		return null;
	}

	@Override
	public Project saveProject(ProjectDTO projectDTO) {

		// First save project details in project table
		Long createdBy = customUserDetailsService.getCurrentLoggedInUserId();
		Project project = new Project();
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setCreatedBy(createdBy);

		Set<Team> teams = new HashSet<Team>();

		// Then save associated teams for project in project_team_map table
		projectDTO.getTeamIds().parallelStream().forEachOrdered(teamId -> {
			teams.add(new Team(teamId));
		});

		project.setTeams(teams);

		Project savedProject = projectRepository.save(project);

		// Then save associated ticket types and their workflows for project in
		// project_ticket_type_workflow_map table
		projectDTO.getSupportedticketTypesAndTheirWorkflow().parallelStream().forEach(entry -> {
			String[] entries = entry.split(":");
			projectTicketTypeWorkflowMapRepository.save(new ProjectTicketTypeWorkflowMap(
					new ProjectTicketTypeWorkflowMapId(savedProject.getId(), entries[0], Long.parseLong(entries[1]))));
		});

		return savedProject;
	}

	@Override
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteProject(Long projectId) {
		// Delete all teams for the project from project_team_map table
		projectTeamMapRepository.deleteByProjectId(projectId);

		// Delete all associated ticket types and their workflows for project in
		// project_ticket_type_workflow_map table
		projectTicketTypeWorkflowMapRepository.deleteByProjectId(projectId);

		// Delete project from project table
		projectRepository.deleteById(projectId);
	}

	@Transactional
	@Override
	public Project updateProject(ProjectDTO projectDTO, Long projectId) {

		// Delete all teams for the project from project_team_map table
		projectTeamMapRepository.deleteByProjectId(projectId);

		// Delete all associated ticket types and their workflows for project in
		// project_ticket_type_workflow_map table
		projectTicketTypeWorkflowMapRepository.deleteByProjectId(projectId);

		// Update project name and description in project table
		Long updatedBy = customUserDetailsService.getCurrentLoggedInUserId();
		Project project = new Project();
		project.setId(projectId);
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setUpdatedBy(updatedBy);
		project.setUpdatedOn(LocalDateTime.now());
		Set<Team> teams = new HashSet<Team>();

		// Then save associated teams for project in project_team_map table
		projectDTO.getTeamIds().parallelStream().forEachOrdered(teamId -> {
			teams.add(new Team(teamId));
		});

		project.setTeams(teams);

		Project updatedProject = projectRepository.save(project);

		// Then save associated ticket types and their workflows for project in
		// project_ticket_type_workflow_map table
		projectDTO.getSupportedticketTypesAndTheirWorkflow().parallelStream().forEach(entry -> {
			String[] entries = entry.split(":");
			projectTicketTypeWorkflowMapRepository
					.save(new ProjectTicketTypeWorkflowMap(new ProjectTicketTypeWorkflowMapId(updatedProject.getId(),
							entries[0], Long.parseLong(entries[1]))));
		});

		return updatedProject;
	}
}
