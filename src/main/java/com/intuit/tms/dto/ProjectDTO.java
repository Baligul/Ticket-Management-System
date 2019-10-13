package com.intuit.tms.dto;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ProjectDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String name;

	private String description;

	private Set<Long> teamIds;
	private Map<String, Long> supportedticketTypesAndTheirWorkflow;

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

	public Map<String, Long> getSupportedticketTypesAndTheirWorkflow() {
		return supportedticketTypesAndTheirWorkflow;
	}

	public void setSupportedticketTypesAndTheirWorkflow(Map<String, Long> supportedticketTypesAndTheirWorkflow) {
		this.supportedticketTypesAndTheirWorkflow = supportedticketTypesAndTheirWorkflow;
	}
}
