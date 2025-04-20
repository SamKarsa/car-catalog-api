# 🚗 Catálogo de Automóviles - API Backend

Este proyecto es una API RESTful desarrollada con Java y Spring Boot que permite gestionar un catálogo de automóviles y sus marcas. Proporciona funcionalidades para consultar, crear y filtrar autos, así como validar datos de entrada y registrar logs.

---

## 🧰 Tecnologías utilizadas

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java 17](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/17/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Web**
- **Lombok**
- **MySQL Driver**
- **MapStruct**
- **Hibernate Validator (Jakarta Validation)**

---

## ⚙️ Configuración del proyecto

### 📋 Requisitos Previos

- [JDK 17+](https://adoptium.net/)
- [MySQL 8+](https://dev.mysql.com/downloads/)
- [Maven 3.8+](https://maven.apache.org/)
- IDE recomendado: [VS Code](https://code.visualstudio.com/) con:
  - Extension Pack for Java
  - Spring Boot Extension Pack

---

## 🗂 Configuración del archivo `application.properties`

Debes configurar el archivo `src/main/resources/application.properties` con los siguientes datos:

```properties
spring.application.name=emprendimientosudea <-- Aqui va ek nombre de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/emprendimientosudea
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 📝 Nota: No necesitas crear las tablas manualmente, el sistema las generará automáticamente con base en las entidades JPA definidas en el código.

---

## 🗃️ Base de Datos

Se utiliza **MySQL** como motor de base de datos. Las entidades principales son:

- **Car**: automóvil
- **Brand**: marca

Relación: Un automóvil pertenece a una marca (`ManyToOne`).

---

✍️ Autor

- [**Samuel López Marín**](https://github.com/SamKarsa)  
