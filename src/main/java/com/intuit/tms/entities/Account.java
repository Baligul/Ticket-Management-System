package com.intuit.tms.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account extends BaseEntity {

	@Column(nullable = false)
	@NotNull
	private String name;

	@Column(nullable = false, unique = true)
	@NotNull
	private String username;

	@Column(nullable = false, unique = true)
	@NotNull
	@Email(message = "{errors.invalid_email}")
	private String email;

	@ManyToMany()
	@JoinTable(name = "account_role_map", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<Role> roles;

	@ManyToMany()
	@JoinTable(name = "account_team_map", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<Team> teams;

	@NotNull
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Column(name = "mobile_phone")
	private String mobilePhone;

	@NotNull
	@Size(min = 2, max = 256)
	private String password;
	private String gender;
	private boolean enabled;
	private boolean expired;
	private boolean locked;

	@Column(name = "credentials_expired")
	private boolean credentialsExpired;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	private boolean approved;

	private boolean x;

	@Transient
	private int lastLoginYear;

	@Transient
	private int lastLoginDay;

	@Transient
	private String lastLoginMonth;

	@Transient
	private String lastLoginTime;

	@Transient
	private String lastLoginFormatted;

	public Account() {
	}

	public Account(Long id) {
		this.setId(id);
	}

	public Account(@NotNull String name, @NotNull String username,
			@NotNull @Email(message = "{errors.invalid_email}") String email, Set<Role> roles, Set<Team> teams,
			@NotNull @Pattern(regexp = "(^$|[0-9]{10})") String mobilePhone,
			@NotNull @Size(min = 2, max = 256) String password, String gender, boolean enabled, boolean expired,
			boolean locked, boolean credentialsExpired, LocalDateTime lastLogin, Long createdBy,
			LocalDateTime updatedOn, Long updatedBy, boolean approved, boolean x) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.teams = teams;
		this.mobilePhone = mobilePhone;
		this.password = password;
		this.gender = gender;
		this.enabled = enabled;
		this.expired = expired;
		this.locked = locked;
		this.credentialsExpired = credentialsExpired;
		this.lastLogin = lastLogin;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.approved = approved;
		this.x = x;
	}

	@PrePersist
	private void onCreate() {
		setCreatedOn(LocalDateTime.now());
	}

	@PostLoad
	private void onLoad() {
		if (lastLogin != null) {
			this.lastLoginYear = lastLogin.getYear();
			this.lastLoginDay = lastLogin.getDayOfMonth();
			this.lastLoginMonth = lastLogin.getMonth().toString();
//			this.lastLoginTime = lastLogin.toString("hh:mm a");
			this.lastLoginFormatted = this.lastLoginMonth + " " + this.lastLoginDay + ", " + this.lastLoginYear + " at "
					+ lastLoginTime;
		}
	}

	// Getters and Setters
	// ===================

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isX() {
		return x;
	}

	public void setX(boolean x) {
		this.x = x;
	}
}
