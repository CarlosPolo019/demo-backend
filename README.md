
# Demo Backend Project

Este es un proyecto backend desarrollado con Spring Boot que proporciona una API para gestionar usuarios.

## Descripción

La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre un recurso de usuarios. Utiliza una base de datos MySQL para almacenar la información de los usuarios.

## Requisitos

- Java 17 o superior
- Maven
- MySQL

## Configuración del Proyecto

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/CarlosPolo019/demo-backend.git
   cd demo-backend
   ```

2. **Configurar la base de datos:**

   Asegúrate de tener MySQL en funcionamiento y crea una base de datos llamada `horbath_db`.

3. **Configurar `application.properties`:**

   Edita el archivo `src/main/resources/application.properties` para configurar la conexión a tu base de datos. Aquí tienes un ejemplo:

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

4. **Archivos SQL:**

   El proyecto incluye dos archivos SQL en `src/main/resources`:

   - `schema.sql`: Define la estructura de la tabla `users`.
   - `data.sql`: Contiene datos iniciales que se insertarán en la base de datos al iniciar la aplicación.

   **Puedes ejecutar estos archivos directamente en tu base de datos MySQL para configurar la tabla de usuarios:**
   
   ```sql
   -- Crear la tabla `users` si no existe
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

   **Notas:**
   - El archivo `schema.sql` se utiliza para crear la tabla y definir su estructura.
   - El archivo `data.sql` se puede utilizar para insertar datos iniciales en la tabla `users`.

## Ejecución del Proyecto

Para ejecutar el proyecto, utiliza Maven:

```bash
./mvnw spring-boot:run
```

## API Endpoints

- `GET /api/users`: Obtener la lista de usuarios.
- `POST /api/users`: Crear un nuevo usuario.
- `GET /api/users/{id}`: Obtener un usuario por ID.
- `PUT /api/users/{id}`: Actualizar un usuario.
- `DELETE /api/users/{id}`: Eliminar un usuario.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cambios.

## Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
