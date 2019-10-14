package com.intuit.tms.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Integer priority;

	@ManyToOne()
	@JoinColumn(name = "assignee")
	private Account assignee;

	private Integer severity;

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	private Integer resolution;

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

	public Ticket(Project project, TicketType ticketType, String description, String summary, Integer priority,
			Account assignee, Integer severity, LocalDateTime dueDate, Integer resolution, Status status,
			Long createdBy, LocalDateTime updatedOn, Long updatedBy) {
		super();
		this.project = project;
		this.ticketType = ticketType;
		this.description = description;
		this.summary = summary;
		this.priority = priority;
		this.assignee = assignee;
		this.severity = severity;
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

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Account getAssignee() {
		return assignee;
	}

	public void setAssignee(Account assignee) {
		this.assignee = assignee;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getResolution() {
		return resolution;
	}

	public void setResolution(Integer resolution) {
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
