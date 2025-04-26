1.CompraVentaPisos:
Aplicación Java para la gestión de propiedades inmobiliarias.


2.Descripción:
CompraVentaPisos es un proyecto de consola desarrollado en Java que permite realizar un CRUD completo (Crear, Leer, Actualizar, Eliminar) sobre propiedades almacenadas en una base de datos SQLite.
La aplicación permite añadir nuevas propiedades, ver todas las disponibles, filtrar por estado (Disponible/Vendido), editar y eliminar propiedades.


3.Tecnologías utilizadas:

-Java 21

-SQLite (Base de datos)

-JDBC (Conexión a base de datos)

-Maven (Gestión de dependencias)

-Git (Control de versiones)

-IntelliJ IDEA (Entorno de desarrollo)



4.Funcionalidades principales:

-Ver todas las propiedades registradas.

-Añadir una nueva propiedad.

-Editar propiedades existentes.

-Eliminar propiedades.

-Marcar propiedades como vendidas.

-Filtrar propiedades según su estado.



5.Estructura del proyecto:

CompraVentaPisos/
├── src/main/java/org/example/
│   ├── CompraVentaApp.java    Clase principal con la lógica de la aplicación
│   ├── ConexionBD.java        Clase para la conexión a la base de datos SQLite
│   └── Propiedad.java         Clase modelo que representa una propiedad
├── src/main/resources/
│   └── compraventa.db         Base de datos SQLite (si aplicable)
├── pom.xml                    Archivo Maven para la gestión de dependencias
├── .gitignore                 Archivo de exclusión de Git
└── README.md                  Este archivo



6.Cómo ejecutar el proyecto:

-Clona el repositorio:
git clone https://github.com/Eduardo44817/CompraVenta.git

-Abre el proyecto en IntelliJ IDEA.

-Compila y ejecuta la clase CompraVentaApp.java.

-Sigue las instrucciones que aparecerán en la consola.



7.Requisitos previos:

-JDK 21 instalado.

-Maven instalado y configurado.

-(Opcional) IntelliJ IDEA configurado para proyectos Maven.
