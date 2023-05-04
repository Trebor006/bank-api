FROM adoptopenjdk/maven-openjdk11
EXPOSE 8080
COPY /target/bank-api-0.0.1-SNAPSHOT.jar bank-api.jar
# specify default command
ENTRYPOINT ["java", "-jar", "bank-api.jar"]
