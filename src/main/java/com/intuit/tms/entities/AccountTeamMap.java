package com.intuit.tms.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_team_map")
public class AccountTeamMap extends BaseEntityWithoutId {

	@EmbeddedId
	private AccountTeamMapId accountTeamMapId;

	protected AccountTeamMap() {
	}

	public AccountTeamMap(AccountTeamMapId accountTeamMapId) {
		super();
		this.accountTeamMapId = accountTeamMapId;
	}

	// Getters and Setters
	// ===================

	public AccountTeamMapId getAccountTeamMapId() {
		return accountTeamMapId;
	}

	public void setAccountTeamMapId(AccountTeamMapId accountTeamMapId) {
		this.accountTeamMapId = accountTeamMapId;
	}
}
