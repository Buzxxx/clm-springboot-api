# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file from the deployment directory (Ensure it matches your deploy.yml)
ARG JAR_NAME=contract_lifecycle_management-0.0.1-SNAPSHOT.jar
COPY build/libs/${JAR_NAME} app.jar

# Expose the application's default port (Change if necessary)
EXPOSE 8080

# Run the application with optimizations
CMD ["java", "-jar", "app.jar"]
