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

- A microservice is proposed that uses lightweight protocols such as HTTP or REST API.
- A service-oriented code architecture is proposed with routes, controllers, validators, entities and repository pattern (Clean code).
----

## Technologies
* [Kotlin] - Programming language
* [Spring-boot] - Server 
* [Spring-core] - Framework
* [Git] - Code versioning
* [Maven] - Dependency management
* [IDEA IntelliJ] - IDE
-------

# Installation


| Requires | URL |
| ------ | ------ |
| Git | https://git-scm.com/downloads |
| Docker | https://docs.docker.com/engine/install/ |
| Compose | https://docs.docker.com/compose/install/ |



# API
- Swagger
```
{{ host }}/swagger-ui.html/*
```
- H2 Console
```
user=sa
password=""
jdbc URL = jdbc:h2:mem:testdb
{{ host }}/h2-console/*
```

- Base URL
```
{{ host }}/api/*
```
- Health Check
```
curl -L -X GET 'localhost:8080/api/ecommerce/products/health'
```
- Info
```
{{ host }}/swagger-ui.html/*
```


# Contributors
- [Manuel Garcia Romero](https://www.linkedin.com/in/manuel-alejandro-garcia-romero-01b1b1187/) - Backend Developer / Videogame Developer

# License
This project is property of Tul.


