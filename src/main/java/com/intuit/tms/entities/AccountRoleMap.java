package com.intuit.tms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_role_map")
public class AccountRoleMap extends BaseEntity {
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "role_id")
	private Long roleId;

	protected AccountRoleMap() {
	}

	public AccountRoleMap(Long accountId, Long roleId) {
		super();
		this.accountId = accountId;
		this.roleId = roleId;
	}

	// Getters and Setters
	// ===================

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
