
# Demo Backend Application

## Descripción

Este proyecto es una aplicación backend construida con Spring Boot que permite la gestión de usuarios. Utiliza una base de datos MySQL para almacenar la información de los usuarios y proporciona una API RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los usuarios.

## Requisitos

- Java 11 o superior
- Maven
- MySQL

## Configuración de la Base de Datos

Asegúrate de tener MySQL instalado y en funcionamiento. Crea la base de datos `horbath_db` si no existe. Puedes utilizar el siguiente script SQL para crear la tabla `users`:

```sql
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    deleted_by VARCHAR(100),
    deleted_at DATETIME,
    updated_by VARCHAR(100),
    updated_at DATETIME
);
```

## Configuración de la Aplicación

Edita el archivo `src/main/resources/application.properties` para configurar la conexión a tu base de datos:

```properties
spring.application.name=demo
spring.datasource.url=jdbc:mysql://localhost:3306/horbath_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Ejecutar la Aplicación

Para ejecutar la aplicación, navega hasta la raíz del proyecto en la terminal y utiliza el siguiente comando:

```bash
mvn spring-boot:run
```

## Endpoints de la API

- `GET /api/users`: Obtiene la lista de usuarios.
- `GET /api/users/{id}`: Obtiene un usuario por su ID.
- `POST /api/users`: Crea un nuevo usuario.
- `PUT /api/users/{id}`: Actualiza un usuario existente.
- `DELETE /api/users/{id}`: Elimina un usuario.

## Contribuciones

Las contribuciones son bienvenidas. Siéntete libre de abrir un issue o un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
