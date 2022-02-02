FROM openjdk:11-jre-slim
COPY /target/homework_32_Docker.jar /app/app.jar
LABEL app=app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]