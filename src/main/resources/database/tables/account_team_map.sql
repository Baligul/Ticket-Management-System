CREATE TABLE account_team_map
(
    account_id BIGINT NOT NULL REFERENCES account (id),
    team_id    BIGINT NOT NULL REFERENCES team (id),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT pk_account_team_map PRIMARY KEY (account_id, team_id)
);