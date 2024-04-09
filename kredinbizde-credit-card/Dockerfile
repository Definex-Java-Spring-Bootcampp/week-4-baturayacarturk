FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/kredinbizde-credit-card-0.0.1-SNAPSHOT.jar /app/kredinbizde-credit-card.jar

EXPOSE 8082

CMD ["java", "-jar", "kredinbizde-credit-card.jar"]
