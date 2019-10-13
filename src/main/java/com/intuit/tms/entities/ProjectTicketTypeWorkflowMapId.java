package com.intuit.tms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ProjectTicketTypeWorkflowMapId implements Serializable {
	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "ticket_type")
	private String ticketType;

	@Column(name = "workflow_id")
	private Long workflowId;

	public ProjectTicketTypeWorkflowMapId() {
	}

	// default constructor
	public ProjectTicketTypeWorkflowMapId(Long projectId, String ticketType, Long workflowId) {
		super();
		this.projectId = projectId;
		this.ticketType = ticketType;
		this.workflowId = workflowId;
	}

	// getters, equals() and hashCode() methods
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
}
