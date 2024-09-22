# Étape 1 : Build de l'application
FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /opt/app

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY Dockerfile ./

RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean package -DskipTests

# Étape 2 : Image de production
FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app


COPY --from=build /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
