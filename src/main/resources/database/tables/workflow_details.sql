CREATE TABLE workflow_details
(
    id          BIGSERIAL,
    workflow_id BIGINT NOT NULL REFERENCES workflow (id),
    status_id   BIGINT NOT NULL REFERENCES status (id),
    order       INTEGER,
    created_on  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (workflow_id, staus_id, order),
    CONSTRAINT pk_workflow PRIMARY KEY (id)
);