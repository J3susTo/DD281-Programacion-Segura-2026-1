# Proyecto: UQ AI Solution - Sistema Integral de IA

## Descripción

Plataforma web desarrollada para la gestión de servicios de Inteligencia Artificial, que incluye una Landing Page moderna, formulario de contacto con persistencia en base de datos y un panel de administración con autenticación segura.

## Arquitectura Técnica

- **Backend**: Java con Spring Boot, seguridad con Spring Security (JWT) y cifrado Bcrypt.
- **Frontend**: Next.js 14, React, Tailwind CSS y Axios para la comunicación API.
- **Base de Datos**: H2 Database (en memoria).

## Características Implementadas

1.  **Landing Page**: Diseño responsive con secciones Hero, Servicios, Academia y Lab.
2.  **Seguridad**: Autenticación administrativa mediante JSON Web Tokens (JWT).
3.  **Formularios**: Captura de leads en tiempo real conectados a la base de datos.
4.  **Encriptación**: Contraseñas almacenadas de forma segura utilizando Bcrypt.

## Instrucciones de Ejecución

### 1. Backend

- Navegar a `T_ORTIZ_JUNIOR_backend`.
- Ejecutar: `.\mvnw spring-boot:run`

### 2. Frontend

- Navegar a `uq-ai-frontend`.
- Ejecutar: `npm run dev`

---

_Desarrollado por: Junior Emerzon Ortiz Andrade_
