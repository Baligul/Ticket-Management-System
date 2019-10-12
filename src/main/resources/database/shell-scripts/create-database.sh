#!/bin/bash

# ticket_management_system Database Installation Script
# =====================================================
# PostgreSQL 9.4 on Linux/Mac

# Setup Instructions
# ------------------
#
# Open a terminal, then run the following commands:
# pg_ctl -D /usr/local/var/postgres -l logfile start
#
# vi ~/.pgpass
#
# Add the following 2 lines to .pgpass (Without the first # on each line)
#    #hostname:port:database:username:password
#    localhost:5432:ticket_management_system:tms:TMS!2d7D2f3B
#
# Save and close .pgpass
#
# chmod 600 ~/.pgpass
#
# Close and re-open the terminal
#
# cd ~/repos/ticket-management-system-api/src/main/resources/database/shell-scripts/
#
# ./create-database.sh
#


# Variables
# ------------------------------------------------------------------------------
server=localhost
database=ticket_management_system
port=5432
dbuser=tms

OS=`uname -s`
SUDO=`which sudo`
PSQL=`which psql`
ADMIN_DB="postgres"
ADMIN_ROLE="postgres"

if [ "$OS" == "Darwin" ] ; then
    ADMIN_DB="template1"
    ADMIN_ROLE=`whoami`
fi

# Create the tms user role (use sudo)
# ------------------------------------------------------------------------------
echo "=== Creating role (user) ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../create-role.sql

# Drop and Create the DB as postgres (use sudo)
# ------------------------------------------------------------------------------
echo "=== Dropping old DB (if exists) ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../drop-database.sql
echo "=== Creating new DB ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $ADMIN_DB < ../create-database.sql

# Create tables
# ------------------------------------------------------------------------------
echo "=== Creating tables ==="
echo "- account"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/account.sql
echo "- role"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/role.sql
echo "- account_role_map"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/account_role_map.sql
echo "- persistent_logins"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/persistent_logins.sql
echo "- spring_session"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/spring_session.sql
echo "- team"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/team.sql
echo "- account_team_map"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/account_team_map.sql
echo "- project"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/project.sql
echo "- project_team_map"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/project_team_map.sql
echo "- ticket_type"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/ticket_type.sql
echo "- status"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/status.sql
echo "- workflow"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/workflow.sql
echo "- project_ticket_type_workflow_map"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/project_ticket_type_workflow_map.sql
echo "- ticket"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/ticket.sql
echo "- comment"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/comment.sql
echo "- version"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../tables/version.sql

# Grant role permissions
# ------------------------------------------------------------------------------
echo "=== Granting role permissions ==="
$SUDO -u $ADMIN_ROLE $PSQL -d $database < ../grant-role-permissions.sql

# Default data
# ------------------------------------------------------------------------------

echo "=== Inserting default data ==="
echo "- account"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../data/account.sql
echo "- role"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../data/role.sql
echo "- account_role_map"
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../data/account_role_map.sql

# Update version
# ------------------------------------------------------------------------------

echo "=== Updating version ==="
$PSQL -h localhost -p 5432 -U tms -w -d $database  < ../data/version.sql
