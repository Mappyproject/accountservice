FROM openjdk:17
EXPOSE 8084
COPY target/accountservice-0.0.1-SNAPSHOT.jar accountservice.jar
ENTRYPOINT ["java", "-jar", "/accountservice.jar"]