# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar los archivos de configuración
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código
COPY src ./src

# Construir el proyecto
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el jar desde la etapa de construcción
COPY --from=build /app/target/gestionlab-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la app (ajusta según el que uses, por defecto 8080)
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]