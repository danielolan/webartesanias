# API de Backend para E-commerce


Esta es la API de E-commerce, una plataforma de comercio electrónico que permite a los usuarios realizar compras en línea y gestionar productos, comentarios, direcciones de entrega y usuarios.

# Tabla de Contenidos 

1. Requisitos
2. Configuración
3. Ejecución
4. Documentación
5. Licencia

# Requisitos
Antes de empezar, asegúrate de tener instalados los siguientes requisitos:

1. Java 17
2. Maven
3. PostgreSQL
4. Swagger

# Configuración Base de Datos

1. Crea una base de datos PostgreSQL llamada BackendEcommerce en tu servidor local.
2. Configura las propiedades de la base de datos en el archivo application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/BackendEcommerce
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

# Ejecución
Ejecuta la aplicación Spring Boot utilizando Maven:

mvn spring-boot:run
La aplicación estará disponible en http://localhost:8081.


# Documentación
1. La especificación OpenAPI se encuentra en http://localhost:8081/v3/api-docs.
2. Interfaz de usuario Swagger: http://localhost:8081/swagger-ui/index.html.