CREATE TABLE workflow_details
(
    workflow_id  BIGINT NOT NULL REFERENCES workflow (id),
    status       VARCHAR(256) NOT NULL REFERENCES status (status),
    sequence     INTEGER,
    created_on   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_workflow_details PRIMARY KEY (workflow_id, status)
);