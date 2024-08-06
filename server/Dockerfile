# Use an official Maven image to build the project
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Use a smaller base image for running the application
FROM eclipse-temurin:21

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar /app/server.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "/app/server.jar"]
