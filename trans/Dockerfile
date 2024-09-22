# Étape 1 : Build de l'application
FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

# Étape 2 : Image de production
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/trans-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
