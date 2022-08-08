# acme-tech-challenge
For Phase 3 of the ACME Tech Challenge

## Installing and configuring AWS environment
-- Clone this repository
-- `cd setup/`
-- `terraform apply -var 'db_password=<REPLACE_ME>'`
-- Use values from terraform output to create the following Github 'environment > prod' secrets:
* ECR_REPOSITORY
* ECR_REGISTRY
* DATASOURCE_URL
* DATASOURCE_PASSWORD  (Same as password used above)
* JWT_HEADER_SECRET
* S3_TERRAFORM_BUCKET
* VPC_ID

## Deployment instructions

### Compiling (as necessary)
docker build acme-challenge-api .

### Deployment
Deployments are run automatically by Github actions

## Running tests

## Uninstall instructions
-- Comment out main.tf file and merge PR to main branch to remove ecs components
-- Run `terraform destroy -var 'db_password=<REPLACE_ME>'` from setup folder to remove remaining resources


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
