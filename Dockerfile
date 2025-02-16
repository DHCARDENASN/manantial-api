# Primera etapa: Construcción del JAR
FROM ubuntu:latest AS build

# Actualizar e instalar JDK y dependencias
RUN apt-get update && apt-get install -y openjdk-17-jdk curl unzip

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el código fuente al contenedor
COPY . .

# Dar permisos de ejecución al wrapper de Gradle
RUN chmod +x ./gradlew

# Construir la aplicación
RUN ./gradlew bootJar --no-daemon

# Segunda etapa: Imagen final optimizada para ejecución
FROM eclipse-temurin:17-jdk-alpine

# Establecer directorio de trabajo
WORKDIR /app

# Exponer el puerto 8080
EXPOSE 8080

# Copiar el JAR generado desde la etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]