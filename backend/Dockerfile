FROM maven:amazoncorretto AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins -B

COPY src ./src
RUN mvn package -DskipTests -B

FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]