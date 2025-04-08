# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/Api_Reader_New-0.0.1-SNAPSHOT.jar app.jar

# Expose the same port as defined in application.properties
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
