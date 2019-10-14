CREATE TABLE account_role_map
(
    account_id BIGINT NOT NULL REFERENCES account (id),
    role_id    BIGINT NOT NULL REFERENCES role (id),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_account_role_map PRIMARY KEY (account_id, role_id)
);