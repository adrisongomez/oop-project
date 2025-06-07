# Proyecto de Object Oriented Programming en BIU University

## Resumen del proyecto

La idea es crear una aplicacion de ecommerce

Esta app es una app de concepto y solo por fines de explorar diferentes tecnologias

## Stack

- Java 23.0.2
- Gradle
- Neovim como editor tambien se puede correr en Vscode

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

## TODOs

- [ ] Agregar versiones de todas las herramientas utilizadas
- [ ] Agregar mas descripciones sobre las funcionalidades
- [ ] Posiblemente descope algunas de las cosas que quiero hacer porque aparentamente me puedo ir lejos agregando cosas
