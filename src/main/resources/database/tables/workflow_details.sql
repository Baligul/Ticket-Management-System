CREATE TABLE workflow_details
(
    id           BIGSERIAL,
    workflow_id  BIGINT NOT NULL REFERENCES workflow (id),
    status       VARCHAR(256) NOT NULL REFERENCES status (status),
    sequence     INTEGER,
    created_on   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (workflow_id, status),
    CONSTRAINT pk_workflow_details PRIMARY KEY (id)
);