package com.intuit.tms.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "team")
public class Team extends BaseEntity {

	@Column(nullable = false, unique = true)
	@NotNull
	private String name;

	private String description;

	@Column(name = "team_type")
	private int teamType;

	@ManyToMany(mappedBy = "teams")
	private Set<Account> accounts;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	public Team() {
	}

	public Team(@NotNull String name, String description, int teamType, Set<Account> accounts, Long createdBy,
			LocalDateTime updatedOn, Long updatedBy) {
		super();
		this.name = name;
		this.description = description;
		this.teamType = teamType;
		this.accounts = accounts;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
}
