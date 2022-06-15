FROM openjdk:11
ADD target/docker-system-events-service.jar docker-system-events-service.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "docker-system-events-service.jar"]