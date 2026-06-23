# UQ AI Solution Platform

## Descripción

UQ AI Solution Platform es una plataforma web corporativa desarrollada para la Evaluación Parcial del curso Programación Segura (DD281) de la Universidad Autónoma del Perú.

La solución implementa una Landing Page moderna para la empresa UQ AI Solution Company, junto con un sistema seguro de autenticación, autorización basada en roles (RBAC), gestión de leads y panel administrativo.

El proyecto aplica conceptos de Programación Segura utilizando JWT, BCrypt, Cookies HttpOnly, Spring Security y control de acceso por roles.

---

# Arquitectura del Proyecto

```text
UQ-AI-SOLUTION
│
├── uq-ai-backend
│   ├── Spring Boot 3
│   ├── Spring Security
│   ├── JWT
│   ├── JPA / Hibernate
│   └── H2 Database
│
└── uq-ai-frontend
    ├── Next.js 14
    ├── TypeScript
    ├── Tailwind CSS
    └── Middleware JWT
```

---

# Tecnologías Utilizadas

## Frontend

- Next.js 14
- TypeScript
- Tailwind CSS
- Axios
- Lucide React

## Backend

- Java 17
- Spring Boot 3.x
- Spring Security 6
- Spring Data JPA
- Hibernate
- H2 Database
- JWT (jjwt)
- BCryptPasswordEncoder
- Lombok

## DevOps

- Git
- GitHub

---

# Funcionalidades Implementadas

## Landing Page

- Hero Section
- Navbar Responsive
- UQ AI Solutions
- UQ AI Academy
- UQ AI Lab
- Formulario de Contacto
- Footer Corporativo

## Seguridad

- Autenticación JWT
- Contraseñas cifradas con BCrypt
- Cookies HttpOnly
- SameSite=Strict
- Secure en producción
- Middleware de protección
- RBAC (Role Based Access Control)

## Dashboard

### ADMIN

- Visualizar todos los leads registrados.
- Visualizar usuarios.

### USER

- Visualizar únicamente su perfil.

---

# Endpoints REST

## Autenticación

### Registrar usuario

POST

```http
/api/auth/register
```

### Login

POST

```http
/api/auth/login
```

---

## Usuarios

### Listar usuarios

GET

```http
/api/usuarios
```

Acceso:

```text
ADMIN
```

### Obtener usuario por ID

GET

```http
/api/usuarios/{id}
```

Acceso:

```text
ADMIN o propietario
```

---

## Leads

### Registrar lead

POST

```http
/api/leads
```

Acceso:

```text
Público
```

### Listar leads

GET

```http
/api/leads
```

Acceso:

```text
ADMIN
```

---

# Conceptos de Programación Segura Aplicados

## BCrypt

Las contraseñas nunca se almacenan en texto plano.

```java
passwordEncoder.encode(password)
```

---

## JWT

Los usuarios autenticados reciben un token firmado.

```text
Authorization: Bearer <token>
```

---

## Cookies HttpOnly

El JWT se almacena en una cookie HttpOnly para evitar robo mediante JavaScript.

```text
HttpOnly = true
SameSite = Strict
Secure = true (producción)
```

---

## RBAC

Roles implementados:

```text
ADMIN
USER
```

---

## Protección de Rutas

Middleware de Next.js:

```text
/dashboard
/admin
```

requieren autenticación.

---

# Base de Datos

Motor:

```text
H2 Database
```

Consola:

```text
http://localhost:8080/h2-console
```

---

# Ejecución Local

## Backend

Entrar al proyecto:

```bash
cd uq-ai-backend
```

Ejecutar:

```bash
mvn spring-boot:run
```

Servidor:

```text
http://localhost:8080
```

---

## Frontend

Entrar al proyecto:

```bash
cd uq-ai-frontend
```

Instalar dependencias:

```bash
npm install
```

Ejecutar:

```bash
npm run dev
```

Servidor:

```text
http://localhost:3000
```

---

# Variables de Entorno Frontend

Archivo:

```text
.env.local
```

Contenido:

```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

---

# Testing

Pruebas implementadas:

- LeadServiceTest
- UsuarioServiceTest

Validaciones realizadas:

- Registro de usuarios
- Consulta de usuarios
- Registro de leads
- Control de acceso por roles

---

# Autor

Jesús Tolentino

Universidad Autónoma del Perú

Curso: Programación Segura DD281

Semestre: 2026-1

---

# Estado del Proyecto

Proyecto funcional:

- Backend Seguro
- Landing Page
- Login Seguro
- JWT
- Cookies HttpOnly
- Dashboard
- RBAC
- Logout Seguro