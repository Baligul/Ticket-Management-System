-- Store Spring RememberMe sessions in PostgreSQL
--

CREATE TABLE persistent_logins
(
    username  VARCHAR(64) NOT NULL,
    series    VARCHAR(64),
    token     VARCHAR(64) NOT NULL,
    last_used TIMESTAMP   NOT NULL,

    CONSTRAINT pk_persistent_logins PRIMARY KEY (series)
);

COMMENT ON TABLE persistent_logins IS 'Spring RememberMe';