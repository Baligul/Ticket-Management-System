package com.intuit.tms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProjectTeamMapId implements Serializable {
	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "team_id")
	private Long teamId;

	public ProjectTeamMapId() {
	}

	// default constructor
	public ProjectTeamMapId(Long projectId, Long teamId) {
		super();
		this.projectId = projectId;
		this.teamId = teamId;
	}

	// getters, equals() and hashCode() methods

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectTeamMapId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
