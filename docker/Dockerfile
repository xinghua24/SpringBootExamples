FROM openjdk:8-jre-alpine
VOLUME /tmp
EXPOSE 8080
ADD /target/DemoSpringBootApp.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]