package com.intuit.tms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_team_map")
public class AccountTeamMap extends BaseEntity {
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "team_id")
	private Long teamId;

	protected AccountTeamMap() {
	}

	public AccountTeamMap(Long accountId, Long teamId) {
		super();
		this.accountId = accountId;
		this.teamId = teamId;
	}

	// Getters and Setters
	// ===================

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
