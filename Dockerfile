# decimos que tenemos una dependencia en java
FROM openjdk:17-jdk-slim

# ¿Quién mantiene esta madre?
MAINTAINER software-alchemist.com

# Copio el jar de target al docker
COPY target/accounts-service-0.0.1-SNAPSHOT.jar accounts-service-0.0.1-SNAPSHOT.jar

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "accounts-service-0.0.1-SNAPSHOT.jar"]