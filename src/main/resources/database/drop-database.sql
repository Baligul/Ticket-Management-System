-- Drop the database
-- The multiple lines below are necessary b/c the Postgresql server has a safety
-- check where it won't drop a DB if there are any existing connections. The
-- error message will look like:
-- ERROR:  database "ticket_management_system" is being accessed by other users
-- DETAIL:  There are 12 other sessions using the database.

UPDATE pg_catalog.pg_database SET datallowconn=false WHERE datname='ticket_management_system';
--SELECT pg_catalog.pg_terminate_backend(procpid) FROM pg_catalog.pg_stat_activity WHERE datname='ticket_management_system';
DROP DATABASE IF EXISTS ticket_management_system;
