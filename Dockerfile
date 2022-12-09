FROM maven:3.6.3-jdk-8 as build
WORKDIR /workspace/app
COPY pom.xml .
COPY src src
RUN mvn install && chmod +x ./*.jar
ENTRYPOINT ["java","-jar","/target/*.jar"]