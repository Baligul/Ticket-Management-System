CREATE TABLE project_team_map
(
    project_id BIGINT NOT NULL REFERENCES project (id),
    team_id    BIGINT NOT NULL REFERENCES team (id),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_project_team_map PRIMARY KEY (project_id, team_id)
);