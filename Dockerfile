FROM eclipse-temurin:8-jre-centos7
RUN mkdir /opt/app
COPY target/hello-world-0.0.1.jar /opt/app/app.jar
COPY *.yml /opt/app
WORKDIR /opt/app
CMD ["java", "-jar", "/opt/app/app.jar", "server", "/opt/app/hello-world.yml"]
EXPOSE 8080 8081