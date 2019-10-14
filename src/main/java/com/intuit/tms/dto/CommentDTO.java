package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class CommentDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String comment;

	// Getters and Setters
	// ===================

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}