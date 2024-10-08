-- Crear la base de datos `horbath_db` si no existe
CREATE DATABASE IF NOT EXISTS horbath_db;

-- Usar la base de datos `horbath_db`
USE horbath_db;

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
