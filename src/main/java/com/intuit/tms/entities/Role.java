package com.intuit.tms.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
	@Column(nullable = false, unique = true)
	@NotEmpty
	private String role;

	// Notice that we use "roles" not the table name of "role". "roles" is from
	// Account.roles.
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
	private List<Account> accounts;

	protected Role() {
	}

	public Role(@NotEmpty String role) {
		super();
		this.role = role;
	}

	// Getters and Setters
	// ===================

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
