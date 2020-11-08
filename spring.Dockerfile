FROM adoptopenjdk/openjdk11:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} /app/spring-app.jar

EXPOSE 8080:8080

ENTRYPOINT ["java", "-jar", "/app/spring-app.jar"]