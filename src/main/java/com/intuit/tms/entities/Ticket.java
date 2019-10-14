package com.intuit.tms.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.intuit.tms.enums.TicketPriorityEnum;
import com.intuit.tms.enums.TicketResolutionEnum;

@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {

	@ManyToOne()
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne()
	@JoinColumn(name = "ticket_type")
	private TicketType ticketType;

	private String description;
	private String summary;
	private TicketPriorityEnum priority;

	@ManyToOne()
	@JoinColumn(name = "assignee")
	private Account assignee;

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	private TicketResolutionEnum resolution;

	@ManyToOne()
	@JoinColumn(name = "status")
	private Status status;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	public Ticket() {
	}

	public Ticket(Long id) {
		this.setId(id);
	}

	public Ticket(Project project, TicketType ticketType, String description, String summary,
			TicketPriorityEnum priority, Account assignee, LocalDateTime dueDate, TicketResolutionEnum resolution,
			Status status, Long createdBy, LocalDateTime updatedOn, Long updatedBy) {
		super();
		this.project = project;
		this.ticketType = ticketType;
		this.description = description;
		this.summary = summary;
		this.priority = priority;
		this.assignee = assignee;
		this.dueDate = dueDate;
		this.resolution = resolution;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
	}

	// Getters and Setters
	// ===================

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public TicketPriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(TicketPriorityEnum priority) {
		this.priority = priority;
	}

	public Account getAssignee() {
		return assignee;
	}

	public void setAssignee(Account assignee) {
		this.assignee = assignee;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public TicketResolutionEnum getResolution() {
		return resolution;
	}

	public void setResolution(TicketResolutionEnum resolution) {
		this.resolution = resolution;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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
