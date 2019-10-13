package com.intuit.tms.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project_team_map")
public class ProjectTeamMap extends BaseEntityWithoutId {
	@EmbeddedId
	private ProjectTeamMapId projectTeamMapId;

	protected ProjectTeamMap() {
	}

	public ProjectTeamMap(ProjectTeamMapId projectTeamMapId) {
		this.projectTeamMapId = projectTeamMapId;
	}

	// Getters and Setters
	// ===================

	public ProjectTeamMapId getProjectTeamMapId() {
		return projectTeamMapId;
	}

	public void setProjectId(ProjectTeamMapId projectTeamMapId) {
		this.projectTeamMapId = projectTeamMapId;
	}
}
