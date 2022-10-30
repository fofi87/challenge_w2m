# Challenge W2M

API desarrollada en Spring Boot 2 y Java 11 que permite hacer el mantenimiento de súper héroes.

## Ejecución

Para ejecutar la aplicación es necesario tener docker-compose instalado y correr los siguientes comandos:

```bash
mvn clean install
```
```bash
docker-compose build
```
```bash
docker-compose up
```

Para poder hacer uso de la API es necesario obtener un token, para eso hay que consumir el endpoint ***localhost:8080/api/authenticate*** el cual simula una autenticación y devuelve un token válido.

## Urls de interés

- Consola h2: http://localhost:8080/api/h2-console

- Swagger: http://localhost:8080/api/swagger-ui/