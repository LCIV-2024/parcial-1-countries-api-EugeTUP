FROM openjdk:17-jdk-alpine
COPY target/lciii-scaffolding-0.0.1-SNAPSHOT.jar lciii-scaffolding-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "lciii-scaffolding-0.0.1-SNAPSHOT.jar"]