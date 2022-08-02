Install Docker desktop
cd acme , and execute following command to start local mysql db in docker
	docker-compose -f dc-mysql.yml up
update application.properties with correct docker port
To build
	mvn clean install
To start
	Run AcmeApplication as JavaApplication
or
    mvn spring-boot:run
To test
Navigate to http://localhost:8088
