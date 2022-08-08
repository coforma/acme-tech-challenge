#!/bin/bash
DATASOURCE_URL=$1
DATASOURCE_PASSWORD=$2
JWT_HEADER_SECRET=$3

echo '''
#app settings
server.port=80

#docker mysql url, port changes after every build
spring.datasource.url=jdbc:mysql://'$DATASOURCE_URL':3306/acme?createDatabaseIfNotExist=true
spring.datasource.username=skywalker
spring.datasource.password='$DATASOURCE_PASSWORD'
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#hibernate settings
spring.jpa.defer-datasource-initialization=true
spring.batch.jdbc.initialize-schema=always
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.generate_statistics=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL55Dialect
#logging.level.org.hibernate.type=trace
#logging.level.org.hibernate.stat=debug

#actuator settings
management.endpoints.web.exposure.include=*

logging.level.root=INFO
logging.level.com.acme=INFO
springdoc.swagger-ui.disable-swagger-default-url=true
jwt.header.secret='$JWT_HEADER_SECRET'
''' > ./acme.deployment.properties