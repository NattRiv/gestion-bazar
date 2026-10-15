# gestion-bazar
API REST con Spring Boot y JPA para gestionar productos, clientes y ventas de un bazar. Proyecto final del curso de TodoCode Academy.

## Tecnologías

- Java 25
- Spring Boot 4.1.1
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Postman

## Funcionalidades

### Productos

- Crear, consultar, editar y eliminar productos.

- onsultar productos con stock menor a 5 unidades.

- Validar los datos de los productos.

- Actualizar el stock según las ventas realizadas.

### Clientes

- Crear, consultar, editar y eliminar clientes.

- Validar los datos de los clientes.

### Ventas

- Crear, consultar, editar y eliminar ventas.

- Consultar los productos asociados a una venta.

- Consultar las ventas realizadas en una fecha determinada.

- Obtener la venta de mayor monto.

Agregado:
- Actualizar el stock al crear, editar o eliminar una venta.

## Arquitectura

El proyecto está organizado en capas:

```
src/main/java/.../
├── controller   → Endpoints REST
├── service      → Lógica de negocio
├── repository   → Acceso a datos (JPA)
├── model        → Entidades
├── dto          → Objetos de transferencia de datos
└── mapper       → Conversión entre entidades y DTOs
└── exception    → Manejo de errores
```
## Configuración de la base de datos

El proyecto utiliza **MySQL** como sistema de gestión de bases de datos.

Antes de ejecutar la aplicación, es necesario:

1. Iniciar el servidor de **MySQL**.
2. Crear la base de datos correspondiente al proyecto.
3. Configurar la **URL de conexión, el usuario y la contraseña** en las propiedades de la aplicación o mediante las variables de entorno configuradas.
4. Verificar que las credenciales sean correctas.

> **Importante:** No publicar contraseñas ni credenciales privadas en el repositorio.

## Ejecución del proyecto

1. **Clonar el repositorio** desde GitHub.
2. **Configurar la conexión con MySQL**.
3. Abrir el proyecto en **Eclipse** u otro IDE compatible con Spring Boot.
4. Ejecutar la clase principal de la aplicación.
5. Verificar que la aplicación se haya iniciado correctamente.

### URL base de la API

```text
http://localhost:8080
```

## Endpoints principales

### Productos

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/productos` | Listar productos |
| GET | `/productos/{id}` | Obtener un producto |
| POST | `/productos/crear` | Crear un producto |
| PUT | `/productos/editar/{id}` | Editar un producto |
| DELETE | `/productos/eliminar/{id}` | Eliminar un producto |
| GET | `/productos/falta-stock` | Productos con stock menor a 5 |

### Clientes

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/clientes` | Listar clientes |
| GET | `/clientes/{id}` | Obtener un cliente |
| POST | `/clientes/crear` | Crear un cliente |
| PUT | `/clientes/editar/{id}` | Editar un cliente |
| DELETE | `/clientes/eliminar/{id}` | Eliminar un cliente |

### Ventas

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/ventas` | Listar ventas |
| GET | `/ventas/{id}` | Obtener una venta |
| POST | `/ventas/crear` | Crear una venta |
| PUT | `/ventas/editar/{id}` | Editar una venta |
| DELETE | `/ventas/eliminar/{id}` | Eliminar una venta |
| GET | `/ventas/productos/{id}` | Productos de una venta |
| GET | `/ventas/dia/{fecha}` | Ventas de una fecha |
| GET | `/ventas/mayor-venta` | Venta de mayor monto |

## Pruebas con Postman

El proyecto incluye una **colección de Postman** con las solicitudes utilizadas para probar los endpoints de la API REST.

**Ubicación:** `postman/Bazar.postman_collection.json`

### ¿Cómo importar la colección?

1. Abrir **Postman**.
2. Seleccionar **Import**.
3. Importar el archivo JSON ubicado en la carpeta `postman`.
4. Iniciar la aplicación y ejecutar las solicitudes.

La colección permite probar las operaciones **CRUD**, las consultas específicas y los casos de error.

## Manejo de errores

La API utiliza **excepciones personalizadas** para gestionar situaciones como recursos inexistentes y falta de stock.

Entre los códigos de estado HTTP utilizados se encuentran:

- **`200 OK`**: consulta o edición exitosa.
- **`201 Created`**: creación exitosa.
- **`204 No Content`**: eliminación exitosa.
- **`400 Bad Request`**: solicitud inválida o falta de stock.
- **`404 Not Found`**: recurso inexistente.
Ubicación: postman/Bazar.postman_collection.json

## Autora

**Natalia Belen Rivas** — Técnica Universitaria en Programación (UTN Buenos Aires)
