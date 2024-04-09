FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/notification-service-0.0.1-SNAPSHOT.jar /app/notification-service.jar

EXPOSE 8088

CMD ["java", "-jar", "notification-service.jar"]
