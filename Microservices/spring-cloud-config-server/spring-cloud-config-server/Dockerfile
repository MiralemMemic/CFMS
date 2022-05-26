FROM openjdk:11
ADD target/docker-spring-cloud-config-server.jar docker-spring-cloud-config-server.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "docker-spring-cloud-config-server.jar"]