FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY build/libs/ms-warehouse-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
