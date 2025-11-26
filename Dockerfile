FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar archivos del proyecto
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw .

# Dar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Construir el JAR
RUN ./mvnw clean package -DskipTests

# Copiar el JAR
COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
