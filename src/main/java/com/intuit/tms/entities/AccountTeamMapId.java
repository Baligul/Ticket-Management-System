package com.intuit.tms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class AccountTeamMapId implements Serializable {
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "team_id")
	private Long teamId;

	public AccountTeamMapId() {
	}

	// default constructor
	public AccountTeamMapId(Long accountId, Long teamId) {
		super();
		this.accountId = accountId;
		this.teamId = teamId;
	}

	// getters, equals() and hashCode() methods
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
