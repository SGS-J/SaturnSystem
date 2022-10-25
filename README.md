# SaturnSystem

Sistema de gestión talento humano proyecto SENA.

## Instalación

Clonar el repositorio.    
    
    git clone https://github.com/SGS-J/SaturnSystem.git
  
Instalar base de datos MySql con el script "DATABASE_INSTALLATION.sql".

Cambiar las credenciales de usuario de la base de datos en el archivo "application.properties"

    spring.datasource.username=[nombre de usuario]
    spring.datasource.password=[contraseña]
    
Ejecutar el comando para iniciar la aplicación con Gradle.

    cd SaturnSystem
    .\gradlew run
Para generar el archivo Jar el comando es el siguiente:

    .\gradlew clean
    .\gradlew build

El Jar ejecutable se encuentra en build/libs/Saturn-0.0.1-SNAPSHOT.Jar.
Importante NO ejecutar la version -plain si no la versión completa, con todas las librerías.

## Frameworks y Librerías Usadas
*	[Spring](https://spring.io)
*	[Spring Boot](https://spring.io/projects/spring-boot)
*	[Spring Session](https://spring.io/projects/spring-session)
*	[JavaFX](https://openjfx.io)
*	[MapStruct](https://mapstruct.org)
*	[Json-Simple](https://code.google.com/archive/p/json-simple/)
*	[Apache Commons-IO](https://commons.apache.org/proper/commons-io/)
*	[MySQL Connector-Java](https://mvnrepository.com/artifact/mysql/mysql-connector-java)

## Empaquetador y Despliegue
*   [Gradle](https://gradle.org)
*   [Launch4j](https://launch4j.sourceforge.net)
