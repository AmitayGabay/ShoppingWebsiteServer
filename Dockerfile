# Use the official Maven image to build the application
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src /app/src
RUN mvn package

# Use a minimal JRE image to run the application
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/Users_System-0.0.1-SNAPSHOT.jar /app/Users_System-0.0.1-SNAPSHOT.jar

# Expose port 9000
EXPOSE 9000

# Run the JAR file
CMD ["java", "-jar", "Users_System-0.0.1-SNAPSHOT.jar"]
