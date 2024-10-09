# Start from openjdk:17-jdk-alpine as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Install Maven
RUN apk update && apk add --no-cache maven

# Verify Maven installation
RUN mvn -v

# Copy your project files (pom.xml and src folder)
COPY pom.xml .
COPY src ./src

# Run Maven to build your application
RUN mvn clean package -DskipTests

# Expose port 8081
EXPOSE 8081

# Run the JAR file created by Maven
ENTRYPOINT ["java", "-jar", "/app/target/ToDoApp-0.0.1-SNAPSHOT.jar"]