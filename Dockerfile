FROM openjdk:21-slim
EXPOSE 8080
ARG JAR_FILE=target/bikini-validation-*.jar
COPY ${JAR_FILE} bikini-validation.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker-local","-jar","/bikini-validation.jar"]

#docker build -t bikini-validation .
