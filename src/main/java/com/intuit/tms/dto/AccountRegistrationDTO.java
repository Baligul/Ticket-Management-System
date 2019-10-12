package com.intuit.tms.dto;

import com.intuit.tms.constraint.FieldMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
		@FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match") })
public class AccountRegistrationDTO {

	@NotNull
	private String name;

	@NotNull
	@Size(min = 2, max = 256)
	private String password;

	@NotNull
	private String confirmPassword;

	@Column(nullable = false, unique = true)
	@NotNull
	@Email(message = "{errors.invalid_email}")
	private String email;

	@Email
	@NotNull
	private String confirmEmail;

	@AssertTrue
	private Boolean terms;

	private String username;

	@NotNull
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String mobilePhone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}