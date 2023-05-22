FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} marketplace.jar
ENTRYPOINT ["java","-jar","/marketplace.jar"]