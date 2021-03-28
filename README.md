#Ecommerce > ShopingCar
---
La prueba consiste en un Ecommerce sencillo, su trabajo consiste en realizar el módulo correspondiente al carrito de compras, este módulo cuenta con los siguientes requerimientos


Un producto debe contener nombre, sku, descripción y precio.
Existen dos tipos de productos, el producto simple y el producto con descuento. El precio del producto con descuento es el ingresado divido por 2
Un carrito debe contener la lista de los productos asociada a él, cada producto en el carrito debe tener su cantidad correspondiente (ej: Pintura 6 unidades, cemento 3 unidades, etc) y un estado, pendiente o completado según sea el caso.
Los identificadores de las entidades deben ser un UUID
El manejo de los estados debe hacerse con enumerables

### Las funcionalidades esperadas son las siguientes:
- Lista de todos los productos

- Agregar, eliminar y modificar productos

- Lista de todos los productos de un carrito

- Agregar, eliminar y modificar productos al carrito

- Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a completado.

### Duración máxima de la prueba

- 7 días

### Entregables

- Repositorio con el código fuente.



### Puntos a revisar

- Buenas prácticas en el código y aplicación de los principios SOLID

- Inyección de dependencias por constructor

- Validación del request en el controlador

---
## Content

- [Architecture used](#Architecture)
- [Technologies and tools](#Technologies)
- [Installation](#Installation)
- [API](#API)
- [Coverage](#Coverage)
- [Contributors](#Contributors)
- [License](#License)

----

# Architecture

- A microservice is proposed that uses lightweight protocols such as HTTP or REST API and use Redis as a Distributed Cache of High-Performance (Microservices Architecture).
- A service-oriented code architecture is proposed with routes, controllers, validators, entities and repository pattern (Clean code).
- This REST service uses a caching strategy for balanced performance of external APIs
----

## Technologies
* [Kotlin] - Programming language
* [Spring-boot] - Server 
* [Spring-core] - Framework
* [Git] - Code versioning
* [Gradle] - Dependency management
* [IDEA IntelliJ] - IDE
-------

# Installation


| Requires | URL |
| ------ | ------ |
| Git | https://git-scm.com/downloads |
| Docker | https://docs.docker.com/engine/install/ |
| Compose | https://docs.docker.com/compose/install/ |



## Build and Run containers

Run all containers
```
$ make docker-build-run
```
OR

Run only services containers
```
$ make docker-run-services
```
in other terminal
```
$ make docker-run-geoip-api
```


Down containers
```
$ make docker-down
```
or
```
$ make docker-down-all
```

## Developing application locally

### Run server
If your machine not has environments of repository then do:

```
export $(cat .env.example | grep -v ^# | xargs)
```

Use spring profiles to debug/run application not in container locally. For this reason use run command:

```
./gradlew  clean build -Dspring.profiles.active=local
```

To run stand-alone application as a jar-file use this command:
```
java -Duser.timezone=America/Bogota -Dspring.profiles.active=local -jar build/libs/geo*jar
```

For Intellij or Any IDE editor, please setup environment variables and the following properties:
```
-Dspring.profiles.active=local
```


### Build and Tests

Run redis container for testing
```
$ make docker-redis-tests
```
and After ...

1. Test your changes
```
$ ./gradlew test 
```

2. Build the project without Test
```
$ ./gradlew -x test build 
```

3. Build the project complete
```
$ ./gradlew build 
```

Finally

```
$ make docker-down-redis-tests
```


## Environments

For deployment you has set the next environments (examples)

- EXCHANGE_RATE_API_KEY=<api_key>
- APP_BASE_URL=http://127.0.0.1:8080
- REQUESTS_TIMEOUT=1000
- REDIS_HOST=127.0.0.1
- REDIS_PORT=6379

Note: to obtain an ExchangeRate API_KEY you must register and access the dashboard https://app.exchangerate-api.com/dashboard

(The port by default that work the service is 8080)

# API

- Base URL
```
{{ host }}/api/*
```
- Health Check
```
curl -L -X GET 'localhost:8080/api/geo-ip/health'
```
- Info
```
curl -L -X GET 'localhost:8080/api/geo-ip/info'
```


# Coverage
- Unit test with JUnit4 and JaCoCo for coverage code
  ![Coverage](jacoco.png)


# Test stress and load
- Load and stress tests with 10000 requests by second as number of examples
  ![Coverage](jmeter.png)


# Contributors
- [Manuel Garcia Romero](https://www.linkedin.com/in/manuel-alejandro-garcia-romero-01b1b1187/) - Backend Developer / Videogame Developer

# License
This project is property of Tul.


