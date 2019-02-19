FROM adoptopenjdk/openjdk8-openj9:latest
RUN mkdir -p /demo
COPY target/demo-1.0-SNAPSHOT.jar /demo
WORKDIR /demo
ENTRYPOINT ["java", "-jar", "demo-1.0-SNAPSHOT.jar"]