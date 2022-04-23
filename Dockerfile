FROM openjdk:11-jdk-slim as builder

WORKDIR /app

COPY . .

RUN ./gradlew bootJar

RUN ls

FROM openjdk:11-jdk-slim

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar .
CMD java -jar -Dspring.profiles.active=docker-compose *.jar