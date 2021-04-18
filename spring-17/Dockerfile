FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/spring-17.jar
WORKDIR /usr/local/run
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]