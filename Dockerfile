FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

RUN groupadd -r appuser && useradd -r -g appuser --no-create-home --shell /bin/false appuser

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

RUN chown appuser:appuser app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]