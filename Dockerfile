FROM maven:3.8-openjdk-15-slim AS builder
COPY ./acme /app/
WORKDIR /app/
RUN mvn clean install
   
FROM amazoncorretto:15-al2-jdk
LABEL org.opencontainers.image.authors="coforma.io"
COPY --from=builder /app/target/acme-0.0.1-SNAPSHOT.jar acme-server-1.0.0.jar
COPY ./acme/src/main/resources/application-deployment.properties acme.application.properties
ENTRYPOINT ["java","-jar","/acme-server-1.0.0.jar", "--spring.config.location=acme.application.properties"]