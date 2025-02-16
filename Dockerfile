# Etapa de construcción
FROM maven:3.8.6-openjdk-17 AS build

# Configurar directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y resolver dependencias primero (mejora rendimiento)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar todo el código fuente del proyecto
COPY . .

# Construir el JAR
RUN mvn clean package -DskipTests

# Etapa final (imagen más liviana)
FROM openjdk:17-jdk-slim
WORKDIR /app

# Exponer puerto (Render lo asignará dinámicamente)
EXPOSE 8080

# Copiar el JAR generado desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
