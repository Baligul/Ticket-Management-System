package com.intuit.tms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class AccountRoleMapId implements Serializable {
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "role_id")
	private Long roleId;

	public AccountRoleMapId() {
	}

	// default constructor
	public AccountRoleMapId(Long accountId, Long roleId) {
		super();
		this.accountId = accountId;
		this.roleId = roleId;
	}

	// getters, equals() and hashCode() methods
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
