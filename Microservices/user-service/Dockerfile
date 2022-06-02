FROM openjdk:11
ADD target/docker-user-service.jar docker-user-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "docker-user-service.jar"]