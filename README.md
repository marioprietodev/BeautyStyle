# BeautyStyle - Sistema de Gestion ERP para Centros de Estetica

Proyecto desarrollado para el segundo curso de DAM (Desarrollo de Aplicaciones Multiplataforma). BeautyStyle es una aplicacion web completa diseñada para la gestion de citas, usuarios y catalogo de servicios, con un enfoque en la seguridad y la arquitectura limpia.

Acceso al proyecto desplegado: https://beautystyle-production.up.railway.app/login

---

## Stack Tecnologico

El proyecto utiliza un stack moderno para asegurar la estabilidad y el despliegue en entornos reales:

* Backend: Java 21 con Spring Boot 3.x
* Seguridad: Spring Security (Control de sesiones y encriptacion de contraseñas con BCrypt).
* Persistencia: Spring Data JPA e Hibernate.
* Base de Datos: MySQL.
* Frontend: Thymeleaf y Bootstrap 5 (Interfaz responsive).
* Despliegue: Railway (Integracion continua con este repositorio).

---

## Funcionalidades Principales

* Seguridad basada en roles (RBAC): Implementacion de perfiles ADMIN y USER con accesos restringidos mediante configuracion de seguridad.
* Gestion de Citas y Servicios: Logica de negocio para la reserva de huecos y administracion de servicios.
* Validacion de Datos: Uso de Bean Validation (@NotBlank, @Size, @Email) para asegurar la integridad en el servidor.
* Arquitectura MVC: Estructura organizada en Controladores, Servicios y Repositorios para facilitar el mantenimiento del codigo.

---

## Usuarios de Prueba (Demo)

Para probar las funcionalidades de administracion y usuario sin necesidad de registro:

* Administrador: admin / 1234
* Usuario: user / 1234

(Nota: Se recomienda usar el perfil de Administrador para visualizar la gestion de servicios y el panel de control).

---

## Configuracion Local

1. Clonar repositorio: git clone https://github.com/marioprietodev/BeautyStyle.git
2. Configurar base de datos en src/main/resources/application.properties (ver archivo .example de guia).
3. Ejecutar: ./mvnw spring-boot:run

---

Contacto
LinkedIn: https://www.linkedin.com/in/marioprietobenitez
GitHub: https://github.com/marioprietodev
