# acme-tech-challenge
For Phase 3 of the ACME Tech Challenge

## Installing and configuring AWS environment

## Deployment instructions

### Compiling (as necessary)

### Deployment

## Running tests

## Uninstall instructions

(From AWS environment.)

## API verification instructions

## Usage instructions

How to query database, any other manual steps necessary to evaluate the solution.

## User.MD file information

## Local Project Setup
--Import mvn pom xml file into IDE (Eclipse or IntelliJ)

## Running application locally
--Install Docker:  https://docs.docker.com/get-docker/
--Install MySQL via Docker Compose:
    -from terminal/cmd line cd to project folder /acme-tech-challenge/acme
    -run 'docker-compose -f dc-mysql.yml up'
    -run maven commands:  'mvn clean install'
    -start up application: 'java -jar acme-0.0.1-SNAPSHOT.jar'
--Swagger API testing:  
    -Authenticate credentials: http://localhost/login
    -Navigate web browser to:  http://localhost/swagger-ui/index.html


(Possibly? If needed? Might be kind to link to it.)
