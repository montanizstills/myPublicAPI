FROM maven:3.6.3-jdk-8 as build
ARG home=/workspace/app
WORKDIR $home
COPY pom.xml .
COPY src src
RUN mvn install
ENTRYPOINT ["java","-jar","target/myPublicAPI-1.0-SNAPSHOT.jar"]