FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/*.jar /dev/versions/allo-application.jar

EXPOSE 8080

CMD ["java", "-jar", "/versions/allo-application.jar"]
