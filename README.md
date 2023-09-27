GestorTareaApi
API para gestionar tareas y sus asignaciones a usuarios.

Prerrequisitos
Java 17 (OpenJDK 17 recomendado)
PostgreSQL
Configuración
Base de datos
Instale PostgreSQL.

Ejecute el siguiente script SQL para configurar la base de datos:

sql
Copy code
CREATE DATABASE gestor_tareas;
CREATE ROLE usuario_tarea WITH LOGIN PASSWORD '12345';
GRANT ALL PRIVILEGES ON DATABASE gestor_tareas TO usuario_tarea;
ALTER ROLE usuario_tarea WITH SUPERUSER;
Al iniciar la aplicación por primera vez, se ejecutará automáticamente un archivo data.sql para poblar la base de datos con información básica.

Credenciales
Los usuarios pueden autenticarse utilizando los siguientes datos:

Usuario Administrador:
Email: seba@gmail.com
Contraseña: 12345
Rol: ROLE_ADMIN
Usuario Común:
Email: pruebas@gmail.com
Contraseña: 12345
Rol: ROLE_USER
Nota: Las contraseñas están codificadas utilizando BCrypt en la base de datos.

Cómo ejecutar
Compilar y empaquetar:

bash
Copy code
mvn clean install
Ejecutar:

bash
Copy code
java -jar target/nombre-del-artifacto.jar
Endpoints y cómo probar
Se recomienda usar herramientas como Postman o CURL para probar los endpoints. A continuación, se muestran algunos ejemplos con CURL:

Listar todas las tareas:

bash
Copy code
curl -X GET http://localhost:8080/tarea/
Buscar tarea por ID:

bash
Copy code
curl -X GET http://localhost:8080/tarea/{id}
(Continuar con otros ejemplos de CURL según los endpoints)

Seguridad
La autenticación se realiza mediante tokens JWT. Dependiendo del perfil del usuario (ROLE_ADMIN o ROLE_USER), se tienen diferentes niveles de acceso a los endpoints. Esta gestión se lleva a cabo en el archivo SecurityConfig.

Comentarios adicionales
Enfoque: Esta API se centra en la gestión de tareas y su asignación a usuarios. Se ha utilizado una estructura modular para separar las responsabilidades y facilitar la mantenibilidad.

Decisiones de diseño: Opté por usar Spring Boot debido a su facilidad para crear aplicaciones web y RESTful rápidamente. La seguridad se gestiona mediante JWT para asegurar los endpoints y proporcionar autenticación.

Tecnologías y bibliotecas específicas:

Spring Boot: Para la estructura general de la aplicación y la inyección de dependencias.
JWT: Para la autenticación y generación de tokens seguros.
PostgreSQL: Como sistema de gestión de bases de datos.
Lombok: Para reducir el boilerplate en las entidades y DTOs.
Jakarta: Para la gestión de transacciones.
