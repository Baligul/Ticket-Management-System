package com.intuit.tms.services;

import com.intuit.tms.entities.Project;

import java.util.List;

import com.intuit.tms.dto.ProjectDTO;

public interface ProjectService {
	Project getProjectByName(String name);

	Project getProjectById(Long id);

	Project saveProject(ProjectDTO projectDTO);

	List<Project> getAllProjects();

	void deleteProject(Long id);

	Project updateProject(ProjectDTO projectDTO, Long projectId);
}