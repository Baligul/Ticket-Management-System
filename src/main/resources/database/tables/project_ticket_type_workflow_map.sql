CREATE TABLE project_ticket_type_workflow_map
(
    project_id     BIGINT NOT NULL REFERENCES project (id),
    ticket_type    VARCHAR(256) NOT NULL REFERENCES ticket_type (ticket_type),
    workflow_id    BIGINT NOT NULL REFERENCES workflow (id),
    created_on     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_project_ticket_type_workflow_map PRIMARY KEY (project_id, ticket_type, workflow_id)
);