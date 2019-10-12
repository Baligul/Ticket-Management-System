CREATE TABLE account_role_map
(
    id         BIGSERIAL,
    account_id BIGINT NOT NULL REFERENCES account (id),
    role_id    BIGINT NOT NULL REFERENCES role (id),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    UNIQUE (account_id, role_id),
    CONSTRAINT pk_account_role_map PRIMARY KEY (id)
);