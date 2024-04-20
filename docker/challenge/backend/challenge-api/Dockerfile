FROM amazoncorretto:21-alpine-jdk

LABEL authors="rmnzs"
LABEL purpose="the simplest dockerfile for the challenge in SOAT course"
EXPOSE 8080

WORKDIR /api

COPY ./build/libs/challenge-api-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "challenge-api-0.0.1-SNAPSHOT.jar"]
