CREATE TABLE team
(
    id                  BIGSERIAL,
    name                VARCHAR(256) NOT NULL,
    description         VARCHAR(256),
    team_type           INTEGER,
    created_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,
    approved            BOOLEAN DEFAULT FALSE,

    CONSTRAINT pk_team PRIMARY KEY (id)
);
