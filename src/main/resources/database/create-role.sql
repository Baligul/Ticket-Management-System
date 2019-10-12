DO
$body$
BEGIN
    IF NOT EXISTS(
            SELECT *
            FROM pg_catalog.pg_user
            WHERE usename = 'tms')
    THEN
        CREATE ROLE tms WITH PASSWORD 'TMS!2d7D2f3B';
    END IF;
END
$body$;

ALTER ROLE tms NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION LOGIN;
