FROM openjdk:11
ADD target/docker-notification-service.jar docker-notification-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "docker-notification-service.jar"]