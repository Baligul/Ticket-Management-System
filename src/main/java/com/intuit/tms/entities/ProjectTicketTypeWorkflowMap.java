package com.intuit.tms.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "project_ticket_type_workflow_map")
public class ProjectTicketTypeWorkflowMap extends BaseEntityWithoutId {

	@EmbeddedId
	private ProjectTicketTypeWorkflowMapId projectTicketTypeWorkflowMapId;

	protected ProjectTicketTypeWorkflowMap() {
	}

	// Getters and Setters
	// ===================

	public ProjectTicketTypeWorkflowMap(ProjectTicketTypeWorkflowMapId projectTicketTypeWorkflowMapId) {
		super();
		this.projectTicketTypeWorkflowMapId = projectTicketTypeWorkflowMapId;
	}

	public ProjectTicketTypeWorkflowMapId getProjectTicketTypeWorkflowMapId() {
		return projectTicketTypeWorkflowMapId;
	}

	public void setProjectTicketTypeWorkflowMapId(ProjectTicketTypeWorkflowMapId projectTicketTypeWorkflowMapId) {
		this.projectTicketTypeWorkflowMapId = projectTicketTypeWorkflowMapId;
	}
}
