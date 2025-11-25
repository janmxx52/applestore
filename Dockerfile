FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

# FIX QUYỀN CHO mvnw
RUN chmod +x mvnw

# Build bằng Maven Wrapper
RUN ./mvnw -q -DskipTests package

CMD ["java", "-jar", "target/applestore-0.0.1-SNAPSHOT.jar"]
