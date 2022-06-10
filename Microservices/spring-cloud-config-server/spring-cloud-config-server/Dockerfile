FROM openjdk:11
RUN apt-get update && apt-get install && apt-get clean
ADD target/docker-spring-cloud-config-server.jar docker-spring-cloud-config-server.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "docker-spring-cloud-config-server.jar"]
