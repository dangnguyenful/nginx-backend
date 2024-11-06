# Use the official jdk image.
FROM openjdk:17-jdk-alpine

# Create and change to the app directory.
WORKDIR /usr/src/app

COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

# Your app binds to port 8081
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/myapp.jar"]
