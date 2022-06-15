FROM openjdk:11
ADD target/docker-message-service.jar docker-message-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-message-service.jar"]