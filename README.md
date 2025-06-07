# Proyecto de Object Oriented Programming en BIU University

## Resumen del proyecto

La idea es crear una aplicacion de ecommerce

Esta app es una app de concepto y solo por fines de explorar diferentes tecnologias

## Stack

- Java 23.0.2
- Gradle
- Neovim como editor tambien se puede correr en Vscode

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

