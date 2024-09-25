# Use an official Tomcat base image with JDK 21
FROM tomcat:9.0-jdk21

# Set environment variables for MongoDB connection
ENV MONGO_HOST=mongo
ENV MONGO_PORT=27017
ENV MONGO_DB_NAME=mydb

# Copy the Spring Boot WAR file to the Tomcat webapps directory
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose Tomcat's default port
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
