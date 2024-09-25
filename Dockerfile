FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY *.jar allo-application.jar

EXPOSE 8080

CMD ["java", "-jar", "allo-application.jar"]
