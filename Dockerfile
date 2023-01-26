FROM openjdk:11-jdk
COPY build/libs/springboot-template.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]