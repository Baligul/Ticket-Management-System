CREATE TABLE workflow
(
    id                  BIGSERIAL,
    title               VARCHAR(256) UNIQUE,
    created_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,

    CONSTRAINT pk_workflow PRIMARY KEY (id)
);
