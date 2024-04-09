FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/akbank-service-0.0.1-SNAPSHOT.jar /app/akbank-service.jar

EXPOSE 8083

CMD ["java", "-jar", "akbank-service.jar"]
