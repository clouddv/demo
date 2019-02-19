FROM adoptopenjdk/openjdk8-openj9:latest
RUN mkdir -p /demo
COPY target/*.jar /demo
WORKDIR /demo
ENTRYPOINT ["java", "-jar", ".jar"]