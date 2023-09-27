📌 Gestor de Tareas API

Este proyecto es una API de gestión de tareas que permite crear, modificar, eliminar y asignar tareas a usuarios.

🚀 Instrucciones para ejecución local

📦 Prerrequisitos

  * OpenJDK: Debes tener instalado el OpenJDK 17.
* PostgreSQL: Asegúrate de tener instalada la base de datos PostgreSQL.

🔧 Configuración de la Base de Datos
Lanza tu cliente PostgreSQL y ejecuta el siguiente script para preparar la base de datos:

    CREATE DATABASE gestor_tareas;
    CREATE ROLE usuario_tarea WITH LOGIN PASSWORD '12345';
    GRANT ALL PRIVILEGES ON DATABASE gestor_tareas TO usuario_tarea;
    ALTER ROLE usuario_tarea WITH SUPERUSER;
    Una vez preparada la base de datos, el sistema ejecutará automáticamente el archivo data.sql interno para poblarla con información básica.

🔑 Autenticación

POST /authenticate

Autentica a un usuario y retorna un token JWT para su posterior uso en la API.

Parámetros del cuerpo de la solicitud:

username (requerido): Nombre de usuario.
password (requerido): Contraseña del usuario.

Ejemplo del cuerpo de la solicitud:

    {
        "username": "nombreUsuario",
        "password": "contraseñaUsuario"
    }

Respuestas:

200 OK: Si la autenticación es exitosa. Se retornará el token JWT.



Ejemplo de respuesta exitosa:
    
    {
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJST0xFUyI6IltST0xFX1VTRVJdIiwic3ViIjoicHJ1ZWJhc0BnbWFpbC5jb20iLCJpYXQiOjE2OTU3OTY5ODIsImV4cCI6MTY5NTgxNDk4Mn0.Wfq9qkkv84e6bfTVVnArmiAG9y_8fzExKonq797fjOc",
        "refreshToken": null,
        "email": "pruebas@gmail.com"
    }

401 Unauthorized: Si la autenticación falla, por ejemplo, debido a credenciales incorrectas.

Endpoints de TareaController:

IMPORTANTE! para utilizar los endpoint debe tener la firma jwt 

Listar todas las tareas

    Método: GET
    Ruta: /tarea/
    Descripción: Retorna una lista con todas las tareas.
    
    Buscar tarea por ID
    Método: GET
    Ruta: /tarea/{id}
    Descripción: Retorna una tarea basada en su ID. Si la tarea no se encuentra, retorna un estado NOT_FOUND.
    
    Crear una nueva tarea
    Método: POST
    Ruta: /tarea/
    Descripción: Crea una nueva tarea. Se requiere que el estado tenga un ID. Si la tarea ya existe (basada en el título), retorna un error.
    
    Modificar una tarea existente
    Método: PUT
    Ruta: /tarea/{id}
    Descripción: Modifica una tarea existente basada en su ID. Se requiere que el estado tenga un ID. Si la tarea no se encuentra, retorna un estado NOT_FOUND.
    
    Eliminar una tarea
    Método: DELETE
    Ruta: /tarea/{id}
    Descripción: Elimina una tarea basada en su ID.
    
    Asignar una tarea a un usuario
    Método: POST
    Ruta: /tarea/usuario/{usuarioId}/tarea/{tareaId}
    Descripción: Asigna una tarea específica a un usuario específico. Si el usuario o la tarea no se encuentran, retorna un estado NOT_FOUND.
    
    Eliminar la asignación de una tarea a un usuario
    Método: DELETE
    Ruta: /tarea/usuario/{usuarioId}/tarea/{tareaId}
    Descripción: Elimina la asignación de una tarea específica de un usuario específico. Si el usuario o la tarea no se encuentran, retorna un estado NOT_FOUND.

Comentarios adicionales

Enfoque: Esta API se centra en la gestión de tareas y su asignación a usuarios. Se ha utilizado una estructura modular para separar las responsabilidades y facilitar la mantenibilidad.

Decisiones de diseño: Opté por usar Spring Boot debido a su facilidad para crear aplicaciones web y RESTful rápidamente. La seguridad se gestiona mediante JWT para asegurar los endpoints y proporcionar autenticación.

Tecnologías y bibliotecas específicas:

Spring Boot: Para la estructura general de la aplicación y la inyección de dependencias.
Spring Security: para la seguridad de la aplicacion y permisos
Spring JPA : para el mapeo de tablas y relaciones
JWT: Para la autenticación y generación de tokens seguros.
PostgreSQL: Como sistema de gestión de bases de datos.
Jakarta: Para la gestión de transacciones.
