FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD /target/DemoSpringBootApp.jar /app/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/DemoSpringBootApp.jar"]
