FROM openjdk:17-jdk-slim

LABEL maintainer="David kangnigabiam720@gmail.com"

EXPOSE 8761

COPY target/discovery_service-0.0.1-SNAPSHOT.jar discovery_service.jar

ENTRYPOINT ["java", "-jar", "discovery_service.jar"]