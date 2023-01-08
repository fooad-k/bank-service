FROM openjdk:17-alpine
WORKDIR /app/
COPY /target/*-SNAPSHOT.jar ./project.jar
EXPOSE 8081
CMD ["java","-jar","project.jar"]


