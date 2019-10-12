package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TeamDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String name;

	private String description;

	private int teamType;

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

	public int getTeamType() {
		return teamType;
	}

	public void setTeamType(int teamType) {
		this.teamType = teamType;
	}
}