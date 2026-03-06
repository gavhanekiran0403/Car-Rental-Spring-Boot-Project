FROM eclipse-temurin:17-jdk-alpine
EXPOSE 5151
ADD target/Car-Rental-Management-System-0.0.1-SNAPSHOT.jar Car-Rental-Management-System-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Car-Rental-Management-System-0.0.1-SNAPSHOT.jar"]