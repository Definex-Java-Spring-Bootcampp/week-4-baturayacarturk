FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-loan-0.0.1-SNAPSHOT.jar /app/kredinbizde-loan.jar

EXPOSE 8087

CMD ["java", "-jar", "kredinbizde-loan.jar"]
