CREATE TABLE project_team_map
(
    id         BIGSERIAL,
    project_id BIGINT NOT NULL REFERENCES project (id),
    team_id    BIGINT NOT NULL REFERENCES team (id),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (project_id, team_id),
    CONSTRAINT pk_project_team_map PRIMARY KEY (id)
);