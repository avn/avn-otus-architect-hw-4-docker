FROM gradle:6.3.0-jdk8 as builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -i

FROM openjdk:8-jre-alpine
WORKDIR /app
RUN apk add liquibase -X http://dl-cdn.alpinelinux.org/alpine/edge/testing && \
    apk add java-postgresql-jdbc
COPY --from=builder ["/app/build/libs" ,"/app/build/resources/main/db-migrations", "./"]
EXPOSE 8000
CMD java -jar hw1-architect-docker-0.0.1-SNAPSHOT.jar
