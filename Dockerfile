FROM openjdk:17-alpine
WORKDIR /app/
COPY /target/*-SNAPSHOT.jar ./bank-service.jar
EXPOSE 8081
CMD ["java","-jar","bank-service.jar"]


