FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src
RUN chmod +x mvnw
RUN ./mvnw package -B

FROM openjdk:17-jdk-slim as runtime
WORKDIR /app
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar app.jar"]
