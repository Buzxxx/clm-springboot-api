# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk AS builder

# Ensure latest source files are copied
WORKDIR /app
COPY . .

# Build the JAR inside the container
RUN ./gradlew clean build -x test

# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set working directory inside the container
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application's default port (Change if necessary)
EXPOSE 8081

# Run the application with optimizations
CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" ,"-jar", "app.jar"]
