**compose.yaml** contains instructions for creating the services that are required by the app (Backend and Database). It also reads the .env file from the root to set database credentials.

**Dockerfile** builds the app by importing java 21 image (**maven:amazoncorretto** and **amazoncorretto:21-alpine**), and downloading all the maven dependencies from pom.xml, and builds the app into containers.

Terminal commands are visualized and explained in [[Docker Commands.canvas]]

