CREATE TABLE ticket
(
    id              BIGSERIAL,
    project_id      BIGINT NOT NULL REFERENCES project (id),
    ticket_type     VARCHAR(256) NOT NULL REFERENCES ticket_type (ticket_type),
    description     VARCHAR(256),
    summary         VARCHAR(256),
    priority        VARCHAR(25),
    assignee        BIGINT NOT NULL REFERENCES account (id),
    due_date        TIMESTAMP,
    resolution      VARCHAR(25),
    status          VARCHAR(256) NOT NULL REFERENCES status (status),
    created_on      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      BIGINT,
    updated_on      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by      BIGINT,

    CONSTRAINT pk_ticket PRIMARY KEY (id)
);
