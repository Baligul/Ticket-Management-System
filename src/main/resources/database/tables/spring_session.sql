-- Store Spring HTTP sessions in PostgreSQL

CREATE TABLE spring_session (
    session_id CHAR(36),
    creation_time BIGINT NOT NULL,
    last_access_time BIGINT NOT NULL,
    max_inactive_interval INT NOT NULL,
    principal_name VARCHAR(100),
    CONSTRAINT spring_session_pk PRIMARY KEY (session_id)
);

CREATE INDEX spring_session_ix1 ON spring_session (last_access_time);

CREATE TABLE spring_session_attributes (
    session_id CHAR(36),
    attribute_name VARCHAR(200),
    attribute_bytes BYTEA,
    CONSTRAINT spring_session_attributes_pk PRIMARY KEY (session_id, attribute_name),
    CONSTRAINT spring_session_attributes_fk FOREIGN KEY (session_id) REFERENCES spring_session(session_id) ON DELETE CASCADE
);

CREATE INDEX spring_session_attributes_ix1 ON spring_session_attributes (session_id);
