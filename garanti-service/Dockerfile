FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/garanti-service-0.0.1-SNAPSHOT.jar /app/garanti-service.jar

EXPOSE 8089

CMD ["java", "-jar", "garanti-service.jar"]
