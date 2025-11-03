# Use a Java 17 base image
FROM maven:4.0.0-rc-4-amazoncorretto-17 AS builder

# Set the working directory inside the container
WORKDIR /app

COPY . .

# Copy the compiled .jar file into the container
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS runtime

# --- ADD THIS LINE ---
# This tells the JVM to start in debug mode and listen on port 5005
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5006"

WORKDIR /app

COPY --from=builder /app/target/*.jar ./app.jar

# Expose the port the app runs on
EXPOSE 8080 5006

# Command to run the application
ENTRYPOINT ["java", "-jar", "./app.jar"]