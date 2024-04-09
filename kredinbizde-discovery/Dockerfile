FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-discovery-0.0.1-SNAPSHOT.jar /app/kredinbizde-discovery.jar

EXPOSE 8766

CMD ["java", "-jar", "kredinbizde-discovery.jar"]
