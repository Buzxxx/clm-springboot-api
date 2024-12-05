#Official Java Runtime
FROM amazoncorretto:21.0.4-alpine3.18

#Set Working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/contract_lifecycle_management-0.0.1-SNAPSHOT.jar app.jar

# Set environment variable for active Spring profile
ENV SPRING_PROFILES_ACTIVE=prod

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]

