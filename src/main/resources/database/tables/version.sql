-- Track the updates applied to the Ticket Management System database

CREATE TABLE version
(
    id         BIGSERIAL,
    major      SMALLINT,
    minor      SMALLINT,
    patch      SMALLINT,
    created_on TIMESTAMP DEFAULT NOW(),

    CONSTRAINT pk_version PRIMARY KEY (id)
);

GRANT ALL PRIVILEGES ON version TO tms;
GRANT ALL PRIVILEGES ON version_id_seq TO tms;
