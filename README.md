📋 API RESTful de Registro de Usuarios

Este proyecto es una API RESTful desarrollada en Java con Spring Boot, diseñada para gestionar el registro y autenticación de usuarios. Incluye validación de correo y contraseñas, generación de tokens JWT, y utiliza una base de datos en memoria H2 para persistencia de datos.

🔗 Repositorio del Proyecto
🛠 Requisitos Previos

Asegúrate de tener instalados:

Java 11
Maven
IntelliJ IDEA o tu IDE favorito
🚀 Configuración del Proyecto

1️⃣ Clonar el Repositorio
```
Copiar código
git clone https://github.com/RicardoCruzParra/PruebaSeleccionBci.git
cd PruebaSeleccionBci
```
2️⃣ Importar el Proyecto en IntelliJ IDEA
Abre IntelliJ IDEA.
Selecciona Open y navega hasta la carpeta del proyecto clonado.
IntelliJ IDEA detectará automáticamente el proyecto Maven y lo configurará.
3️⃣ Configuración del Archivo application.properties
El proyecto incluye una base de datos H2 en memoria. No es necesario realizar cambios en application.properties, pero aquí está el contenido por referencia:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
```
4️⃣ Construir el Proyecto
Para construir el proyecto:

```
mvn clean install
```
💻 Ejecución de la Aplicación

Ejecución en IntelliJ IDEA

Navega hasta la clase principal Application.java.

Haz clic derecho y selecciona Run 'Application'.

La aplicación se ejecutará en http://localhost:8080.

Acceso a la Consola de H2

Accede a la consola de la base de datos H2 en http://localhost:8080/h2-console.

La URL JDBC por defecto es jdbc:h2:mem:testdb, usuario: sa, sin contraseña.

✅ Pruebas Unitarias

Ejecución de Pruebas Unitarias
Ejecuta las pruebas unitarias con:

Desde IntelliJ IDEA:
Abre la ventana Maven en la barra lateral derecha.
Navega a Lifecycle > test y haz clic en Run.
Desde la Terminal:
bash
Copiar código
mvn test
Los resultados se mostrarán en la consola.

Revisión de Resultados
Verifica los resultados de las pruebas en la consola para identificar cualquier fallo y realizar correcciones si es necesario.

📋 Endpoints Principales

Registro de Usuario Caso Exitoso
URL: POST /api/creacionUsuarios/registro
Formato de Solicitud: json

```
{
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.org",
    "contraseña": "P@ssw0rd",
    "telefonos": [
        {
            "numero": "1234567",
            "codigoCiudad": "1",
            "codigoPais": "57"
        }
    ]
}
```

Formato de Respuesta: json
```
{
    "id": "4fd15c60-7e5d-42e3-9733-467e43adfcbd",
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.org",
    "telefonos": [
        {
            "numero": "1234567",
            "codigoCiudad": "1",
            "codigoPais": "57"
        }
    ],
    "creado": "2024-08-14T12:45:00",
    "modificado": "2024-08-14T12:45:00",
    "ultimoIngreso": "2024-08-14T12:45:00",
    "token": "eyJhbGciOiJIUzI1NiIsInR...",
    "estaActivo": true
}
```

Registro de Usuario Caso Existente
URL: POST /api/creacionUsuarios/registro
Formato de Solicitud: json

```
{
    "nombre": "Juan Rodriguez",
    "correo": "juan@rodriguez.org",
    "contraseña": "hunter2",
    "telefonos": [
        {
            "numero": "1234567",
            "codigoCiudad": "1",
            "codigoPais": "57"
        }
    ]
}
```

Formato de Respuesta: json
```
{
    "mensaje": "El correo ya registrado"
}
```

📖 Swagger API Documentation

Documentación interactiva de la API disponible en:

http://localhost:8080/swagger-ui.html

Desde aquí, puedes probar los endpoints directamente en tu navegador.

🤝 Contribuciones

Si deseas contribuir:

Haz un fork del repositorio.
Crea una nueva rama con tus cambios.
Envía un pull request. Revisaremos todas las contribuciones.
📜 Licencia

Este proyecto está bajo la licencia MIT. Lee más en el archivo LICENSE del repositorio.