CREATE TABLE project
(
    id                  BIGSERIAL,
    name                VARCHAR(256) UNIQUE,
    description         VARCHAR(256),
    created_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,

    CONSTRAINT pk_project PRIMARY KEY (id)
);
