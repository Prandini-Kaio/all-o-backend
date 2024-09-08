
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

WORKDIR /app

COPY ${JAR_FILE} allo-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "allo-app.jar"]
