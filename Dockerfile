
#FROM adoptopenjdk/openjdk11:alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#RUN echo "Hello World - Dockerfile"
#RUN echo "Hello World - ${JAR_FILE}"
#COPY ["${JAR_FILE}", "app.jar"]
#RUN bash -c 'touch /app.jar'
##COPY $--from=build rest-with-spring-boot-kotlin-soft-walter/target/*.jar
##ENTRYPOINT ["java","Xmx512m","-Dserver.port=${PORT}","-jar","/app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]



FROM openjdk:17-jdk-slim
ENV BUILDKIT_PROGRESS=plain
MAINTAINER walter
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#COPY target/rest-with-spring-boot-kotlin-soft-walter-0.0.1-SNAPSHOT.jar /app.jar
WORKDIR rest-with-spring-boot-kotlin-soft-walter

RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#FROM openjdk:17-jdk-slim-buster
#WORKDIR /app
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]