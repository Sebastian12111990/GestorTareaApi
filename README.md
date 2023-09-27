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

   API de Tareas 📚
   La API de Tareas permite a los desarrolladores interactuar con un conjunto de operaciones CRUD básicas para gestionar tareas.
   
   ENDPOINT PARA LISTAR TAREAS
   * GET /tarea/
   * Descripción:este endpoint devuelve una lista de tareas. Si no hay tareas disponibles, se devuelve un array vacío.
   * Parámetros:No se requieren parámetros para este endpoint.
   * Respuestas:200 OK
   * Este código de respuesta indica que la solicitud fue exitosa. Se devolverá un array de tareas o un array vacío si no hay tareas disponibles.

   Ejemplo respuesta vacia

         []

   Ejemplo de respuesta con tareas:
  
      [
          {
              "createdAt": "2023-09-27T14:26:34.187+00:00",
              "updatedAt": "2023-09-27T14:26:34.187+00:00",
              "id": 1,
              "titulo": "Modelar BD3",
              "fechaDevencimiento": "26-09-2023 08:00",
              "estado": {
                  "createdAt": "2023-09-27T14:23:03.411+00:00",
                  "updatedAt": "2023-09-27T14:23:03.411+00:00",
                  "id": 1,
                  "estado": "Pendiente"
              },
              "usuarioTareas": []
          },
          {
              "createdAt": "2023-09-27T14:26:57.257+00:00",
              "updatedAt": "2023-09-27T14:26:57.257+00:00",
              "id": 2,
              "titulo": "Programar Backend",
              "fechaDevencimiento": "26-09-2023 08:00",
              "estado": {
                  "createdAt": "2023-09-27T14:23:03.411+00:00",
                  "updatedAt": "2023-09-27T14:23:03.411+00:00",
                  "id": 1,
                  "estado": "Pendiente"
              },
              "usuarioTareas": []
          }
      ]

   Obtener Tarea por ID 🕵️‍♂️
   * GET /tarea/{id}
   * Descripción      : Obtiene la información detallada de una tarea específica, identificada por su ID único.
   * Parámetros id    : ID único de la tarea que se desea recuperar.
   * Respuestas 200 OK: Se devuelve cuando se encuentra la tarea solicitada.
   * 404 Not Found    : Se devuelve cuando no se encuentra la tarea con el ID proporcionado.

   Ejemplo de solicitud
   
         GET http://localhost:8080/tarea/1
   Ejemplos de respuesta 200 OK
   
        {
           "createdAt": "2023-09-27T14:26:34.187+00:00",
           "updatedAt": "2023-09-27T14:26:34.187+00:00",
           "id": 1,
           "titulo": "Modelar BD3",
           "fechaDevencimiento": "26-09-2023 08:00",
           "estado": {
               "createdAt": "2023-09-27T14:23:03.411+00:00",
               "updatedAt": "2023-09-27T14:23:03.411+00:00",
               "id": 1,
               "estado": "Pendiente"
           },
           "usuarioTareas": []
       }
   Ejemplos de repuesta 404 NOT FOUND

         No se proporciona cuerpo en la respuesta.

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
