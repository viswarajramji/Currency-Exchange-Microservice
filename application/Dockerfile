FROM adoptopenjdk/openjdk11:latest
COPY target/application-0.0.1-SNAPSHOT.jar application-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/application-server-1.0.0.jar"]