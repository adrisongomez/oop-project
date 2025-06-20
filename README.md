# Proyecto de Object Oriented Programming en BIU University

## Resumen del proyecto

La idea es crear una aplicacion de ecommerce

Esta app es una app de concepto y solo por fines de explorar diferentes tecnologias

## Stack

- Java 21 (ver configurado en `app/build.gradle.kts`)
- Gradle
- Neovim como editor tambien se puede correr en Vscode

### Librerías y herramientas utilizadas

- **Spring Boot 3.2.5**: framework principal para crear la aplicación web con un servidor embebido y configuración simplificada.
- **Hibernate ORM 6.5.2.Final**: capa de persistencia que nos permite mapear entidades y trabajar con la base de datos usando JPA.
- **Flyway 10.13.0**: herramienta para versionar y aplicar migraciones SQL automáticamente.
- **Driver PostgreSQL 42.7.2**: controlador JDBC usado para conectarse a la base de datos en producción.
- **H2 2.2.224**: base de datos en memoria utilizada durante las pruebas.
- **Guava 33.4.5-jre**: conjunto de utilidades de Google que simplifican colecciones y otras operaciones comunes.
- **dotenv-java 2.3.2**: carga variables de entorno desde un archivo `.env` para la configuración local.
- **jBCrypt 0.4**: permite hashear contraseñas usando el algoritmo BCrypt.
- **java-jwt 4.4.0**: biblioteca para crear y verificar tokens JWT en la autenticación.
- **springdoc-openapi 2.5.0**: genera documentación Swagger automáticamente a partir de los controladores.
- **Gradle 8.14**: herramienta de construcción y gestión de dependencias del proyecto.
- **Docker Compose**: orquesta contenedores para levantar Postgres y otros servicios en desarrollo.

## Docker Compose

El proyecto incluye un `docker-compose.yml` con un contenedor de Postgres para
facilitar las pruebas locales. Para levantar la base de datos ejecuta:

```bash
docker-compose up -d
```

La configuración mínima para Hibernate se encuentra en
`app/src/main/resources/hibernate.cfg.xml` y utiliza las credenciales definidas
en el `docker-compose.yml`. Estos valores pueden sobrescribirse mediante las
siguientes variables de entorno:

- `HIBERNATE_CONN_URL`
- `HIBERNATE_CONN_USERNAME`
- `HIBERNATE_CONN_PASSWORD`
- Opcionalmente `HIBERNATE_DIALECT` y `HIBERNATE_HBM2DDL_AUTO`.

Puedes crear un archivo `.env` basado en `.env.example` con estas variables para
evitar exportarlas manualmente. `HibernateUtil` cargará este archivo
automáticamente si está presente.

### Migraciones con Flyway

Se ha incorporado Flyway para gestionar las migraciones de base de datos. Las
migraciones SQL se encuentran en `app/src/main/resources/db/migration` y se
ejecutan automáticamente al iniciar la aplicación.

#### Cómo crear una nueva migración

1. Crea un archivo `.sql` dentro de `app/src/main/resources/db/migration`.
2. El nombre debe seguir el formato `V<numero>__descripcion.sql`, por ejemplo
   `V2__add_sessions_table.sql`.
3. Incrementa el número de versión en cada nueva migración.

#### Ejecutar migraciones

Las migraciones se aplican cuando `HibernateUtil` inicia, por ejemplo al
ejecutar `./gradlew bootRun` o durante las pruebas con `./gradlew test`.

### Pruebas de integración con base de datos en memoria

Para los tests de integración se utiliza la base de datos en memoria **H2**. Las
migraciones de Flyway se ejecutan automáticamente antes de crear la sesión de
Hibernate mediante `HibernateTestUtil`. No es necesario levantar PostgreSQL para
ejecutar las pruebas, simplemente corre:

```bash
./gradlew test
```

## TODOs

- [ ] Agregar versiones de todas las herramientas utilizadas
- [ ] Agregar mas descripciones sobre las funcionalidades
- [ ] Posiblemente descope algunas de las cosas que quiero hacer porque aparentamente me puedo ir lejos agregando cosas

### Endpoints

La aplicación cuenta con un endpoint básico de revisión de salud:

```
GET /checkhealth
```

que devuelve `{ "status": "ok" }`.

### Documentación Swagger

Se ha integrado `springdoc-openapi` para generar documentación automática.

Al ejecutar la aplicación con Spring Boot, la documentación se puede visitar en:

```
http://localhost:8080/swagger-ui.html
```

