FROM openjdk:11

ADD target/docker-discovery-service.jar docker-discovery-service.jar
RUN apt-get update && apt-get install && apt-get clean
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "docker-discovery-service.jar"]