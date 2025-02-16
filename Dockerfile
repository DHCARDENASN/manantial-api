# Etapa de construcción
FROM ubuntu:latest AS build

# Instalar dependencias
RUN apt-get update && apt-get install -y openjdk-17-jdk curl

# Configurar directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY . .

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Construir la aplicación
RUN ./gradlew bootJar --no-daemon

# Etapa final
FROM openjdk:17-jdk-slim
WORKDIR /app

# Exponer puerto
EXPOSE 8080

# Copiar el JAR generado
COPY --from=build /app/build/libs/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]