FROM openjdk:11
ADD target/docker-api-gateway-service.jar docker-api-gateway-service.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "docker-api-gateway-service.jar"]