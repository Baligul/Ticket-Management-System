CREATE TABLE comment
(
    id                  BIGSERIAL,
    ticket_id           BIGINT NOT NULL REFERENCES ticket (id),
    comment             VARCHAR(256),
    created_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_on          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,

    CONSTRAINT pk_comment PRIMARY KEY (id)
);