FROM openjdk:17-jdk-slim

MAINTAINER meghamalans1996@gmail.com

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#bootstrap command, docker server will execute this , application execution
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]