FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-gw-0.0.1-SNAPSHOT.jar /app/kredinbizde-gw.jar

EXPOSE 8084

CMD ["java", "-jar", "kredinbizde-gw.jar"]
