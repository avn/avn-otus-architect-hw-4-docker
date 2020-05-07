FROM openjdk:8-jre-alpine
WORKDIR /app
COPY /build/libs .
EXPOSE 8000
CMD java -jar hw1-architect-docker-0.0.1-SNAPSHOT.jar
