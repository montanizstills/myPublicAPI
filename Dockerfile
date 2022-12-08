FROM maven:3.6.3-jdk-8 as build
WORKDIR /workspace/app
COPY pom.xml .
COPY src src
ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.nez.MainApplication"]