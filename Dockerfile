FROM openjdk:17-jdk
LABEL authors="andiz"

COPY target/webapp-moviestore.jar .

EXPOSE 8083

ENTRYPOINT ["java","-jar","webapp-moviestore.jar"]
