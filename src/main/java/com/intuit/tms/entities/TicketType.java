package com.intuit.tms.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_type")
public class TicketType extends BaseEntityWithoutId {

	@Id
	@Column(name = "ticket_type")
	private String ticketType;

	private String description;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	public TicketType() {
	}

	public TicketType(String ticketType) {
		this.setTicketType(ticketType);
	}

	public TicketType(String ticketType, String description, Long createdBy, LocalDateTime updatedOn, Long updatedBy) {
		super();
		this.ticketType = ticketType;
		this.description = description;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	// Getters and Setters
	// ===================

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
