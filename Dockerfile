# ==========================================
# Stage 1: Build stage with OpenJDK 17 & Maven
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

# 1. Copy Maven wrapper files and POM for dependency layer caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# 2. Normalize line endings (ensures Windows CRLF doesn't break Linux execution) and make executable
RUN tr -d '\r' < mvnw > mvnw_unix && mv mvnw_unix mvnw && chmod +x mvnw

# 3. Copy application source code
COPY src/ ./src/

# 4. Compile and package the executable JAR, skipping unit tests
RUN ./mvnw clean package -DskipTests -B

# ==========================================
# Stage 2: Runtime stage with lightweight JRE 17
# ==========================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Run as a non-privileged user for container security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy the exact compiled artifact from the builder stage
COPY --from=builder /app/target/SmartTouristSafety-0.0.1-SNAPSHOT.jar app.jar

# Fallback default port for local testing (Render dynamically overrides PORT)
ENV PORT=8081
EXPOSE 8081

# Launch Spring Boot with dynamic PORT support
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]
