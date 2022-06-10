FROM openjdk:11
ADD target/docker-api-gateway-service.jar docker-api-gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-api-gateway-service.jar"]