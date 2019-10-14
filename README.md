### Craft Demo Intuit : Ticket Management System REST APIs using SpringBoot

**ticket-management-system**:

#### How to run?

##### tms> mvn spring-boot:run

##### Go to http://localhost:8080/swagger-ui.html

##### Login with pmteam@intuit.com/admin (or) user@intuit.com/user# security

#### How to setup Database?

#### #!/bin/bash

### ticket_management_system Database Installation Script
### =====================================================
### PostgreSQL 9.4 on Linux/Mac

#### Setup Instructions
#### ------------------
#####
##### Open a terminal, then run the following commands:
##### pg_ctl -D /usr/local/var/postgres -l logfile start (In case PostgreSQL server is not running on Mac system)
#####
##### vi ~/.pgpass
#####
##### Add the following 2 lines to .pgpass (Without the first # on each line)
#####    #hostname:port:database:username:password
#####    localhost:5432:ticket_management_system:tms:TMS!2d7D2f3B
#####
##### Save and close .pgpass
#####
##### chmod 600 ~/.pgpass
#####
##### Close and re-open the terminal
#####
##### cd Ticket-Management-System/src/main/resources/database/shell-scripts/
#####
##### ./create-database.sh
#####
