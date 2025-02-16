FROM Ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY
RUN ./gradlew bootJar --no-Daemon

FROM openjdk-17-jdk-slin
EXPOSE 8080
COPY --from=build /build/libs/web-manantial-api-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]