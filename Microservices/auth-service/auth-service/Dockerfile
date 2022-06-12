FROM openjdk:11
ADD target/docker-auth-service.jar docker-auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-auth-service.jar"]