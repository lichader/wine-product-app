FROM gradle:7.2.0-jdk17 as builder
WORKDIR /app-build
COPY . .
RUN gradle build

FROM openjdk:17-jdk-alpine
WORKDIR /app/
COPY --from=builder /app-build/build/libs/breakdowns-api-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/breakdowns-api-0.0.1-SNAPSHOT.jar"]