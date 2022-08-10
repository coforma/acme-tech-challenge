# acme-tech-challenge
For Phase 3 of the ACME Tech Challenge

## Deployment instructions

The below instructions walk through how to deploy and test this project. The instructions assume that they are being ran on an EC2 instance running the latest amazonlinux 2 AMI, and that the instructions are being ran from within a copy / clone of this git repository on that instance.

We also assume that the amazonlinux2 instance running the below steps has an IAM Role attached to the instance, with a policy matching the policy provided in [infracode/deploy-runner-policy.json](./infracode/deploy-runner-policy.json)

### Install Necessary Tooling

The below instructions require specific tools (mostly terraform, docker, and the aws cli) already be installed on the box. 

To ensure they are installed, please run as a one time action

```shell
./scripts/01-install-deps.sh
```

### Bootstrap Terraform / AWS Environment

Additionally, some initial AWS environment bootstrapping is required to ensure that an appropriate ECR repository (for docker) and S3 bucket (for terraform) are available for the build and deploy code to use.

As a one-time action, please run

```shell
./scripts/02-bootstrap.sh
```

### Build and Deploy

The build and deployment infrastructure is designed to be able to deploy multiple copies within the same AWS account. To distinguish between copies, we use a notion of environments.

To build and deploy the solution with an environment of `submission`, please run

```shell
./scripts/03-deploy.sh --environment submission
```

Additional environment copies can be deployed as desired by varying the value after the `--environment` option.

### Test and Verify

TODO

```shell
./scripts/04-test.sh --environment submission
```

changing `submission` to the environment you wish to test

### Tear Down Environment

When down, each environment can be torn down by running the following,

```shell
./scripts/05-teardown-app.sh --environment submission
```

changing `submission` to the environment you wish to tear down

### Tear Down Bootstrap

When done with the entire solution, the terraform and ecr bootstrap can be torn down with

```shell
./scripts/99-teardown-bootstrap.sh
```

This should only be ran after tearing down each environment per above.
When prompted and you are sure you want to remove the bootstrap code, enter `yes`.

## API verification instructions

## Usage instructions

1.	After deployment is complete.Navigate to home page with url 'http://acme-challenge-dev-894531478.us-east-1.elb.amazonaws.com page redirects' which redirects to swagger-ui page
2.  Application is loaded with seed data for Disaster , PatientStatus , Facility, UserAccount . PLease check import.sql for reference. These information is needed to make payload 
3.  To use api , this application needs an already authenticated Authorization API Key Jwt token in request header needs to sent in for each api http request. JWT Token can be generated with   	following  	steps.
	3.1 invoke POST /auth/login from swagger-ui page with one of the users specified in the list. username is ends with role user has. you can also refer to import.sql for different users and roles
	3.2 this simulates authentication and returns ACME_API_JWT_TOKEN authorization token, which expires in 15 min
4. In swagger-ui page set ACME_API_JWT_TOKEN token returned from previous step by clicking 'Authorize button'
5. To create new PatientDisasterUpdate
	5.1 set authorization header of user with EHR role. only EHR role users have permissions to create a patientdisasterstatus record. other users will get 403
	5.2 Invoke POST /patientStatus/ with sample payload as below 
		{
  			"facilityNpi": 1003906488,
  			"patientIdFromFacility": "piff-2",
  			"disasterId": 1001,
  			"date": "2022-08-05T20:47:44.378Z",
  			"statusId": 101
		}
	5.3 payload restrictions
		5.3.1 currently logged in user's facilityNpi should facilittyNpi that being requested (specified in payload). check UserAccount table data in import.sql for facilityNpi that user has
		5.3.2 patientIdFromFacility can be any text value
		5.3.3 disasterId - should be a disaster that already exists in system. refer import.sql for disaster seed data
		5.3.4 date - date of state up date in format 'YYYY-MM-ddThh:mm:ss.SSSZ' 
		5.3.5 statusId - should be a patient status that already exists in system. refer import.sql for patientstatus seed data 
	5.4 If patient is not already in the system , new patient will be created.
6. To retrieve patient status update 
	6.1 set authorization header of user with EHR or GOVT role. both EHR, GOVT roles users have permissions to get a patientdisasterstatus record. other users will get 403
	6.2 Invoke GET /patientStatus with request params 			"facilityNpi": 1003906488 & "patientIdFromFacility": "piff-2",
  	6.3	Request Params restrictions
  			6.3.1 For EHR role users submitted facilityNpi should match facilityNpi that current user belongs to . please refer imports.sql
  			6.3.2 GOVT role users can get information from any facility
  			
7.Other following urls are for future work , they just return disasters list
	7.1 '/patientStatusByDisaster/{disasterId}/patentList', '/patientStatusByDisaster/{disasterId}/list', '/patientStatusByDisaster/id' , '',''
	
	
			

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
