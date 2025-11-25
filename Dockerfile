# Base image: Java 17 (Alpine = nhỏ gọn, nhanh)
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy toàn bộ source vào container
COPY . .

# Build bằng Maven Wrapper (mvnw) — không chạy test
RUN ./mvnw -q -DskipTests package

# Run ứng dụng bằng file jar đã build
CMD ["java", "-jar", "target/shopgiay-0.0.1-SNAPSHOT.jar"]
