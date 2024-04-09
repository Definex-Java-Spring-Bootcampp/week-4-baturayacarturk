
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-user-0.0.1-SNAPSHOT.jar /app/kredinbizde-user.jar

EXPOSE 8081

CMD ["java", "-jar", "kredinbizde-user.jar"]
