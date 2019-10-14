package com.intuit.tms.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_role_map")
public class AccountRoleMap extends BaseEntityWithoutId {

	@EmbeddedId
	private AccountRoleMapId accountRoleMapId;

	protected AccountRoleMap() {
	}

	public AccountRoleMap(AccountRoleMapId accountRoleMapId) {
		super();
		this.accountRoleMapId = accountRoleMapId;
	}

	// Getters and Setters
	// ===================

	public AccountRoleMapId getAccountRoleMapId() {
		return accountRoleMapId;
	}

	public void setAccountRoleMapId(AccountRoleMapId accountRoleMapId) {
		this.accountRoleMapId = accountRoleMapId;
	}
}
