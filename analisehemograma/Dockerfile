# Use a Java 17 base image
FROM openjdk:17-jdk-slim

# --- ADD THIS LINE ---
# This tells the JVM to start in debug mode and listen on port 5005
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled .jar file into the container
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080 5005

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]