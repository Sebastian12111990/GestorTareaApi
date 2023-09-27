ntrada📌 Gestor de Tareas API

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
   
   Listar Tareas 📋
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


 Crear una nueva Tarea 📝
  * POST /tarea/
  * Descripción : Este endpoint permite crear una nueva tarea. Para ello, es necesario enviar los detalles de la tarea en el cuerpo de la solicitud.
  * Cuerpo de la Solicitud :Es necesario enviar un objeto JSON con los siguientes campos:
  
 Campos
  * titulo: Título de la tarea.
  * fechaDevencimiento: Fecha de vencimiento de la tarea.
  * estado: Objeto que contiene el ID del estado de la tarea.

 Respuestas
  * 200 OK: Se devuelve cuando la tarea se crea con éxito.
  * 400 BAD REQUEST: Se devuelve cuando hay errores en la solicitud o cuando falta información requerida.

Ejemplo de solicitud

     POST http://localhost:8080/tarea/
     Content-Type: application/json

    {
        "titulo":"Programar Backend",
        "fechaDevencimiento": "26-09-2023 08:00",
        "estado" : {
            "id":1
        }
    }
    
Ejemplos de respuesta

Cuando la tarea ya existe:

    {
        "message": "ya existe tarea"
    }

Cuando falta el ID del estado:

     {
         "message": "se necesita estado id"
     }
Cuando hay errores de validación:

    {
        "fechaDevencimiento": "no debe ser nulo",
        "titulo": "no debe estar vacío"
    }
Cuando se crea con éxito:
  
    {
         "createdAt": "fecha de creación",
         "updatedAt": "fecha de actualización",
         "id": "ID de la tarea",
         "titulo": "titulo de la tarea",
         "fechaDevencimiento": "fecha de vencimiento",
         "estado": {
             "id": "ID del estado"
         }
    }
Modificar una Tarea 🛠️
 * PUT /tarea/{id}
 * Descripción: Este endpoint permite modificar una tarea existente. Es necesario enviar el ID de la tarea que se desea modificar en la URL y los detalles de la tarea en el cuerpo de la solicitud.
 * Parámetros id: ID único de la tarea que se desea modificar.

Cuerpo de la Solicitud
Debes enviar un objeto JSON con los campos que desees modificar:

 * titulo: Título de la tarea.
 * fechaDevencimiento: Fecha de vencimiento de la tarea.
 * estado: Objeto que contiene el ID del estado de la tarea.
   
Respuestas
 * 200 OK: Se devuelve cuando la tarea se modifica con éxito.
 * 400 BAD REQUEST: Se devuelve cuando hay errores en la solicitud, falta información requerida o el estado proporcionado no es válido.
 * 404 NOT FOUND: Se devuelve cuando no se encuentra la tarea con el ID proporcionado.

Ejemplo de solicitud

    PUT http://localhost:8080/tarea/1
    Content-Type: application/json
    
    {
        "titulo":"Programar Frontend",
        "fechaDevencimiento": "28-09-2023 12:00",
        "estado" : {
            "id":2
        }
    }
    
Ejemplos de respuesta

Cuando el estado no es válido:

    {
        "message": "Estado no encontrado"
    }
    
Cuando hay errores de validación:
    
    {
        "fechaDevencimiento": "no debe ser nulo",
        "titulo": "no debe estar vacío"
    }
Cuando se modifica con éxito:

    {
      "createdAt": "fecha de creación",
      "updatedAt": "fecha de actualización",
      "id": "ID de la tarea",
      "titulo": "titulo modificado",
      "fechaDevencimiento": "fecha de vencimiento modificada",
      "estado": {
          "id": "ID del estado modificado"
      }
    }
    
404 NOT FOUND: Cuando la tarea no existe.
 * No se proporciona cuerpo en la respuesta.

Eliminar una Tarea 🗑️
 * DELETE /tarea/{id}
 * Descripción: Este endpoint permite eliminar una tarea existente. Para ello, es necesario enviar el ID de la tarea que se desea eliminar en la URL.
 * Parámetros id: ID único de la tarea que se desea eliminar.

Respuestas

  * 200 OK: Se devuelve cuando la tarea se elimina con éxito.
  * 500 INTERNAL SERVER ERROR: Puede devolverse si hay un problema al eliminar la tarea. Sin embargo, este caso no está específicamente manejado en el código proporcionado.
    
Ejemplo de solicitud

        DELETE http://localhost:8080/tarea/1
    
Ejemplos de respuesta

Cuando se elimina con éxito:
 200 OK : No se proporciona cuerpo en la respuesta.


Asignar Usuario a una Tarea 🧑‍💼
 * POST /usuario/{usuarioId}/tarea/{tareaId}
 * Descripción : Este endpoint permite asignar un usuario a una tarea específica. Se requiere enviar tanto el ID del usuario como el ID de la tarea en la URL.
 * Parámetros
     * usuarioId: ID único del usuario que se desea asignar.
     * tareaId  : ID único de la tarea a la que se desea asignar el usuario.
   
Respuestas
  * 200 OK: Se devuelve cuando la asignación es exitosa o si el usuario ya ha sido asignado a la tarea anteriormente.
  * 404 NOT FOUND: Se devuelve cuando el usuario o la tarea no se encuentran en el sistema.

Ejemplo de solicitud

    POST http://localhost:8080/usuario/1/tarea/2
    
Ejemplos de respuesta

Asignación exitosa:

     {
         "message": "usuario Juan Pérez ha sido asignado en la tarea Programar Backend"
     }
     
Error al asignar:

    {
        "message": "error al asignar usuario a tarea"
    }
    
Usuario ya asignado:

    {
        "message": "usuario ya ha sido asignado a esta tarea"
    }
    
Usuario no encontrados:

    {
        "message": "usuario no encontrado"
    }

Tarea no encontrada

    {
        "message": "tarea no encontrada"
    }

Eliminar Asignación de Usuario a Tarea ❌
 * DELETE /usuario/{usuarioId}/tarea/{tareaId}
 * Descripción: Este endpoint elimina la asignación de un usuario a una tarea específica. Se requiere enviar tanto el ID del usuario como el ID de la tarea en la URL.
 * Parámetros
     * usuarioId: ID único del usuario que se desea desasignar.
     * tareaId: ID único de la tarea de la que se desea desasignar el usuario.
     * 
Respuestas
  * 200 OK: Se devuelve cuando la desasignación es exitosa o si el usuario no estaba asignado previamente a la tarea.
  * 404 NOT FOUND: Se devuelve cuando el usuario o la tarea no se encuentran en el sistema.
  * 
Ejemplo de solicitud

        DELETE http://localhost:8080/usuario/1/tarea/2
    
Ejemplos de respuesta

     {
         "message": "usuario eliminado de la tarea"
     }

No había relación previa: entre usuario y tarea

    {
        "message": "relacion entre usuario y tarea no existe nada que eliminar"
    }

Usuario  no encontrados:

    {
        "message": "usuario no encontrado"
    }
Tarea no encontrada

    {
        "message": "tarea no encontrada"
    }



Comentarios Adicionales 📝

 * Enfoque: Nuestra API está diseñada para gestionar tareas y asignarlas a usuarios específicos. Hemos adoptado un diseño modular que separa eficientemente las responsabilidades, facilitando tanto la escalabilidad como la mantenibilidad a largo plazo.
   
 * Decisiones de Diseño 🎨: Elección del Framework: Nos decantamos por Spring Boot debido a su robustez y eficiencia en la creación de aplicaciones RESTful, además de su versátil ecosistema de herramientas.

 * Estrategia de Seguridad: Hemos integrado Spring Security en conjunto con JWT (JSON Web Tokens). Esta combinación nos permite garantizar una autenticación sólida y una gestión eficiente de autorizaciones, asegurando cada uno de los endpoints de nuestra API.

Tecnologías y Bibliotecas Específicas 🛠:

- Spring Boot: Constituye la columna vertebral de nuestra aplicación, proporcionando inyección de dependencias y facilitando la configuración.

- Spring Security: Es fundamental para la gestión de la seguridad en nuestra aplicación, otorgando roles y permisos específicos.

- Spring JPA: Lo hemos implementado para manejar la persistencia y las relaciones entre entidades.

- JWT: Nos ayuda en la autenticación y en la generación segura de tokens.

- PostgreSQL: Hemos elegido este SGBD por su eficiencia y capacidad de manejo de grandes volúmenes de datos.


