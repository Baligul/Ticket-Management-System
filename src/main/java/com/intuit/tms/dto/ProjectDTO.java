package com.intuit.tms.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ProjectDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String name;

	private String description;

	private Set<Long> teamIds;
	private Set<String> supportedticketTypesAndTheirWorkflow;

	// Getters and Setters
	// ===================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Long> getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(Set<Long> teamIds) {
		this.teamIds = teamIds;
	}

	public Set<String> getSupportedticketTypesAndTheirWorkflow() {
		return supportedticketTypesAndTheirWorkflow;
	}

	public void setSupportedticketTypesAndTheirWorkflow(Set<String> supportedticketTypesAndTheirWorkflow) {
		this.supportedticketTypesAndTheirWorkflow = supportedticketTypesAndTheirWorkflow;
	}
}
