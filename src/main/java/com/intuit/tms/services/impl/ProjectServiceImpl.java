package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuit.tms.entities.Project;
import com.intuit.tms.entities.ProjectTeamMap;
import com.intuit.tms.entities.ProjectTeamMapId;
import com.intuit.tms.repositories.ProjectTeamMapRepository;
import com.intuit.tms.repositories.ProjectRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.ProjectService;
import com.intuit.tms.dto.ProjectDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	ProjectTeamMapRepository projectTeamMapRepository;

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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long createdBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Project project = new Project();
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setCreatedBy(createdBy);

		Project savedProject = projectRepository.save(project);

		// Then save associated teams for project in project_team_map table
		projectDTO.getTeamIds().parallelStream().forEachOrdered(teamId -> {
			projectTeamMapRepository.save(new ProjectTeamMap(new ProjectTeamMapId(savedProject.getId(), teamId)));
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
		// Delete project from project table
		projectRepository.deleteById(projectId);

		// Delete all teams for the project from project_team_map table
		projectTeamMapRepository.deleteByProjectId(projectId);
	}

	@Transactional
	@Override
	public Project updateProject(ProjectDTO projectDTO, Long projectId) {

		// Delete all teams for the project from project_team_map table
		projectTeamMapRepository.deleteByProjectId(projectId);

		// Update project name and description in project table
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long updatedBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		Project project = new Project();
		project.setId(projectId);
		project.setName(projectDTO.getName());
		project.setDescription(projectDTO.getDescription());
		project.setUpdatedBy(updatedBy);
		project.setUpdatedOn(LocalDateTime.now());

		// Then save associated teams for project in project_team_map table
		projectDTO.getTeamIds().parallelStream().forEachOrdered(teamId -> {
			projectTeamMapRepository.save(new ProjectTeamMap(new ProjectTeamMapId(projectId, teamId)));
		});

		Project updatedProject = projectRepository.save(project);

		return updatedProject;
	}
}
