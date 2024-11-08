# Use the official jdk image.
FROM openjdk:17-jdk-alpine

# Create and change to the app directory.
WORKDIR /usr/src/app

# Copy application dependency manifests to the container image.
COPY target/ target/

# Your app binds to port 8081
EXPOSE 8081
