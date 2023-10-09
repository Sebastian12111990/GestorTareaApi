

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

Modelo de Datos

![image](https://github.com/Sebastian12111990/GestorTareaApi/assets/60678340/37a73e07-d18b-49a7-b366-ab78dfe48be5)


"Al iniciar la aplicación, el archivo data.sql se ejecutará automáticamente. Una vez que la aplicación se haya levantado, JPA de Spring se encargará de crear y mapear las tablas en la base de datos.
Tras completar este proceso, se llevará a cabo la ejecución del script en data.sql."

existen 2 usuarios para prueba 2 perfiles

    INSERT INTO public.estado_tarea (estado, created_at, updated_at)
    SELECT 'Pendiente', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.estado_tarea WHERE estado = 'Pendiente'
    );
    
    INSERT INTO public.estado_tarea (estado, created_at, updated_at)
    SELECT 'En Progreso', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.estado_tarea WHERE estado = 'En Progreso'
    );
    
    INSERT INTO public.estado_tarea (estado, created_at, updated_at)
    SELECT 'Completada', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.estado_tarea WHERE estado = 'Completada'
    );
    
    INSERT INTO public.usuario (id, nombre, apellido, password, email, created_at, updated_at)
    SELECT 1, 'Sebastian', 'Perez', 'encoded_12345', 'seba@gmail.com', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.usuario WHERE email = 'seba@gmail.com'
    );
    
    INSERT INTO public.usuario (id, nombre, apellido, password, email, created_at, updated_at)
    SELECT 2, 'Luis', 'Pruebas', 'encoded_12345', 'pruebas@gmail.com', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.usuario WHERE email = 'pruebas@gmail.com'
    );
    
    INSERT INTO public.perfil(id, nombre_perfil, description, created_at, updated_at)
    SELECT 1, 'ROLE_ADMIN', 'ROLE_ADMIN', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.perfil WHERE nombre_perfil = 'ROLE_ADMIN'
    );
    
    INSERT INTO public.perfil (id, nombre_perfil, description, created_at, updated_at)
    SELECT 2, 'ROLE_USER', 'ROLE_USER', now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM public.perfil WHERE nombre_perfil = 'ROLE_USER'
    );
    
    INSERT INTO usuarios_perfiles (usuario_id, perfil_id, created_at, updated_at)
    SELECT 1, 1, now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM usuarios_perfiles WHERE usuario_id = 1 AND perfil_id = 1
    );
    
    INSERT INTO usuarios_perfiles (usuario_id, perfil_id, created_at, updated_at)
    SELECT 2, 2, now(), now()
    WHERE NOT EXISTS (
        SELECT 1 FROM usuarios_perfiles WHERE usuario_id = 2 AND perfil_id = 2
    );



🔑 Autenticación con JWT en GestorTareaAPI
    
   * 📖 Descripción : En GestorTareaAPI utilizamos JSON Web Tokens (JWT) para garantizar la autenticación y autorización segura de nuestros usuarios. Al autenticarse con éxito, el usuario recibe un token que debe proporcionarse en las solicitudes subsiguientes para acceder a recursos protegidos.

🌐 Endpoint de Autenticación 
   * POST /authenticate
     
Cuerpo de la Solicitud (Request Body):

    {
        "username": "seba@gmail.com",
        "password": "12345"
    }
    
Respuestas Éxito (200 OK):

    {
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJST0xFUyI6IltST0xFX1VTRVJdIiwic3ViIjoicHJ1ZWJhc0BnbWFpbC5jb20iLCJpYXQiOjE2OTU3OTY5ODIsImV4cCI6MTY5NTgxNDk4Mn0.Wfq9qkkv84e6bfTVVnArmiAG9y_8fzExKonq797fjOc",
        "refreshToken": null,
        "email": "pruebas@gmail.com"
    }
    
Respuestas invalidad
 * Error de Credenciales (500 Internal Server Error): Si el nombre de usuario o la contraseña no son correctos.
 * Datos Faltantes (500 Internal Server Error): Si falta el nombre de usuario o la contraseña en la solicitud.
 * Unauthorized 401 : Si la autenticación falla, por ejemplo, debido a credenciales incorrectas.

Cómo Usar el JWT 🛡
Una vez que hayas obtenido tu JWT, debes incluirlo en el encabezado Authorization de tus solicitudes subsiguientes:

    Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJST0xFUyI6IltST0xFX1VTRVJdIiwic3ViIjoicHJ1ZWJhc0BnbWFpbC5jb20iLCJpYXQiOjE2OTU3OTY5ODIsImV4cCI6MTY5NTgxNDk4Mn0.Wfq9qkkv84e6bfTVVnArmiAG9y_8fzExKonq797fjOc
  
Este token garantiza que estás autenticado y autorizado para acceder a los recursos solicitados.

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
            "createdAt": "2023-09-27T14:57:28.991+00:00",
            "updatedAt": "2023-09-27T14:57:28.991+00:00",
            "id": 3,
            "titulo": "Programar FrontEnd",
            "fechaDevencimiento": "26-09-2023 08:00",
            "estado": {
                "createdAt": "2023-09-27T14:23:03.411+00:00",
                "updatedAt": "2023-09-27T14:23:03.411+00:00",
                "id": 1,
                "estado": "Pendiente"
            }
        },
        {
            "createdAt": "2023-09-27T14:26:57.257+00:00",
            "updatedAt": "2023-09-30T20:07:02.551+00:00",
            "id": 2,
            "titulo": "Modelar BD",
            "fechaDevencimiento": "26-09-2023 16:00",
            "estado": {
                "createdAt": "2023-09-27T14:23:03.414+00:00",
                "updatedAt": "2023-09-27T14:23:03.414+00:00",
                "id": 3,
                "estado": "Completada"
            }
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
         "status": "success",
         "message": "",
         "statusCode": 200,
         "data": {
             "createdAt": "2023-09-27T14:26:57.257+00:00",
             "updatedAt": "2023-09-30T20:07:02.551+00:00",
             "id": 2,
             "titulo": "Modelar BD",
             "fechaDevencimiento": "26-09-2023 16:00",
             "estado": {
                 "createdAt": "2023-09-27T14:23:03.414+00:00",
                 "updatedAt": "2023-09-27T14:23:03.414+00:00",
                 "id": 3,
                 "estado": "Completada"
             }
         },
         "errorDetails": null
     }
     
   Ejemplos de repuesta 404 NOT FOUND

     {
        "status": "info",
        "message": "tarea no encontrada",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
    }


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
        "status": "error",
        "message": "ya existe tarea",
        "statusCode": 400,
        "data": null,
        "errorDetails": null
    }

Cuando falta el ID del estado:

    {
        "status": "error ",
        "message": "Formato de Propiedad invalido -> JSON parse error: Unexpected character ('}' (code 125)): expected a value",
        "statusCode": 400,
        "data": null,
        "errorDetails": null
    }


Cuando hay errores de validación:

    {
        "status": "error",
        "message": "revisar campos nulos",
        "statusCode": 400,
        "data": null,
        "errorDetails": {
            "fechaDevencimiento": "no debe ser nulo",
            "titulo": "no debe estar vacío"
        }
    }
    
Cuando se crea con éxito:
  
    {
        "status": "info",
        "message": "tarea creada con éxito",
        "statusCode": 201,
        "data": {
            "createdAt": "2023-10-09T16:10:54.548+00:00",
            "updatedAt": "2023-10-09T16:10:54.548+00:00",
            "id": 19,
            "titulo": "Programar Front End React",
            "fechaDevencimiento": "26-09-2023 08:00",
            "estado": {
                "createdAt": null,
                "updatedAt": null,
                "id": 1,
                "estado": null
            }
        },
        "errorDetails": null
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
    
Ejemplos de respuestas

Cuando no encuentra una tarea:

    {
        "status": "info",
        "message": "tarea no encontrada con ID : 422",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
    }
    
Cuando hay errores de validación:
     
    {
        "status": "error",
        "message": "revisar campos nulos",
        "statusCode": 400,
        "data": null,
        "errorDetails": {
            "fechaDevencimiento": "no debe ser nulo",
            "titulo": "no debe estar vacío"
        }
    }

Cuando se modifica con éxito:

    {
        "status": "success",
        "message": "tarea modificada",
        "statusCode": 200,
        "data": {
            "createdAt": "2023-09-27T14:26:57.257+00:00",
            "updatedAt": "2023-10-09T16:26:44.044+00:00",
            "id": 2,
            "titulo": "Modificar Tarea",
            "fechaDevencimiento": "26-09-2023 16:00",
            "estado": {
                "createdAt": null,
                "updatedAt": null,
                "id": 3,
                "estado": null
            }
        },
        "errorDetails": null
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
        "status": "success",
        "message": "usuario ha sido asignado en la tarea",
        "statusCode": 200,
        "data": null,
        "errorDetails": null
    }
     
Error al asignar:

    {
      "status": "success",
      "message": "error al asignar usuario a tarea",
      "statusCode": 400,
      "data": null,
      "errorDetails": null
    }
    
Usuario ya asignado:

    {
        "status": "success",
        "message": "usuario ya ha sido asignado a esta tarea",
        "statusCode": 200,
        "data": null,
        "errorDetails": null
    }
    
Usuario no encontrados:

    {
        "status": "info",
        "message": "usuario no encontrado con la ID : 4",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
    }

Tarea no encontrada

    {
        "status": "info",
        "message": "tarea no encontrada con ID : 213321",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
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
        "status": "success",
        "message": "usuario ha sido eliminado de la tarea",
        "statusCode": 200,
        "data": null,
        "errorDetails": null
    }

No había relación previa: entre usuario y tarea

    {
        "status": "info",
        "message": "relacion entre usuario ID: 1 y la tarea ID: 2 no existe ",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
    }

Usuario  no encontrados:

    {
        "status": "info",
        "message": "usuario no encontrado con la ID : 4",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
    }
    
Tarea no encontrada

    {
        "status": "info",
        "message": "tarea no encontrada con ID : 213321",
        "statusCode": 404,
        "data": null,
        "errorDetails": null
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


