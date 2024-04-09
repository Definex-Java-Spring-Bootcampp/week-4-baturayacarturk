FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-application-0.0.1-SNAPSHOT.jar /app/kredinbizde-application.jar

EXPOSE 8080

CMD ["java", "-jar", "kredinbizde-application.jar"]
