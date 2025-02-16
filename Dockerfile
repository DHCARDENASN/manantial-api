# Etapa de construcción
FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

# Etapa final (imagen más liviana)
FROM eclipse-temurin:17-jdk
WORKDIR /app

EXPOSE 8080

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Copiar el archivo JSON de Firebase
COPY serviceAccountKey.json serviceAccountKey.json

ENTRYPOINT ["java", "-jar", "app.jar"]
