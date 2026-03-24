# ---------- Build Stage ----------
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy Gradle wrapper and config first 
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Pre-download dependencies 
RUN ./gradlew build -x test || return 0

# Copy the rest of the project
COPY . .

# Build the application
RUN ./gradlew clean build -x test


# ---------- Run Stage ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]