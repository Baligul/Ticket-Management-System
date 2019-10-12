CREATE TABLE role
(
    id         BIGSERIAL,
    role       VARCHAR(256) NOT NULL,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_role PRIMARY KEY (id)
);

