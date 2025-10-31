# Use a Java 24 base image
FROM maven:4.0.0-rc-4-amazoncorretto-24 AS builder

# --- ADD THIS LINE ---
# This tells the JVM to start in debug mode and listen on port 5005
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

# Set the working directory inside the container
WORKDIR /app

COPY . .

# Copy the compiled .jar file into the container
RUN mvn clean package -DskipTests

FROM openjdk:24-jdk-slim as runtime

WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8080 5005

# Command to run the application
ENTRYPOINT ["java", "-jar", "./app.jar"]