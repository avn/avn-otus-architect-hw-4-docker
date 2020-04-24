FROM gradle:6.3.0-jdk8 as builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -i

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs .
EXPOSE 8000
CMD java -jar hw1-architect-docker-0.0.1-SNAPSHOT.jar
