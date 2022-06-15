FROM openjdk:11
ADD target/docker-prisoner-service.jar docker-prisoner-service.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "docker-prisoner-service.jar"]