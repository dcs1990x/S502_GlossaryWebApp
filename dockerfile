# Building phase:
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Execution phase:
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy Jar file during building phase:
COPY --from=builder /app/target/*.jar app.jar

# Creating a non-root user for safety:
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

EXPOSE 8080

# Running the app:
ENTRYPOINT ["java", "-jar", "/app/app.jar"]