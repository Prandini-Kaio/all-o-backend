FROM openjdk:17-jdk-alpine

WORKDIR /app

RUN mkdir -p /dev/versions/app

COPY target/*.jar /dev/versions/app/allo-application.jar

EXPOSE 8080

CMD ["java", "-jar", "/dev/versions/app/allo-application.jar"]
