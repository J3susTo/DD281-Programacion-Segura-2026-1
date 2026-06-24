# S3 — GUÍA DE TRABAJO DEL ESTUDIANTE
## Autenticación, Gestión de Cookies y Niveles de Acceso

| Campo | Detalle |
|---|---|
| **Curso** | Programación Segura (DD281) — Semana 3 |
| **Nombre del estudiante** | JESUS ANTONIO TOLENTINO VARGAS |
| **Código** | 2221896125 |
| **Fecha de entrega** | _____________ |
| **Tiempo estimado** | 1.5 horas |
| **Puntaje total** | 100 puntos |

---

**Instrucciones generales:**
- Trabaja de forma individual y sin consultar respuestas de otros compañeros
- Responde con tus propias palabras — las respuestas copiadas se anulan
- Las secciones A y B se desarrollan en este documento
- Las secciones C y D requieren respuestas en párrafos completos
- Entrega: plataforma del aula virtual en el formato indicado por el docente

---

## SECCIÓN A — OPCIÓN MÚLTIPLE (20 puntos — 2 pts c/u)

*Marca con una X la alternativa correcta. Una sola respuesta por pregunta.*

---

**Pregunta 1** *(Básica)*
HTTP es un protocolo "sin estado" (stateless). Esto significa que:

- a) El servidor guarda automáticamente el estado de cada usuario entre peticiones
- b) Cada petición HTTP es independiente y el servidor no recuerda peticiones anteriores
- c) El cliente debe reenviar su contraseña en cada petición para identificarse
- d) Solo las peticiones POST mantienen el estado del usuario

**Respuesta: [ b ]**

---

**Pregunta 2** *(Básica)*
¿Cuál de los siguientes mecanismos es el MÁS SEGURO para almacenar el session ID de un usuario?

- a) `localStorage` del navegador
- b) `sessionStorage` del navegador
- c) Cookie con atributos `HttpOnly` y `Secure`
- d) Variable global de JavaScript en el cliente

**Respuesta: [ c  ]**

---

**Pregunta 3** *(Básica)*
El atributo `HttpOnly` en una cookie:

- a) Garantiza que la cookie solo se transmita por HTTPS
- b) Impide que JavaScript del navegador pueda leer el valor de la cookie
- c) Limita la cookie a peticiones del mismo dominio únicamente
- d) Establece la fecha de expiración automática de la cookie

**Respuesta: [  b ]**

---

**Pregunta 4** *(Básica)*
El atributo `Secure` en una cookie garantiza que:

- a) La cookie no puede ser modificada por el usuario
- b) La cookie solo se transmite sobre conexiones HTTPS, nunca HTTP
- c) JavaScript no puede acceder al valor de la cookie
- d) La cookie expira automáticamente al cerrar el navegador

**Respuesta: [  b ]**

---

**Pregunta 5** *(Intermedia)*
Un desarrollador implementa el logout eliminando la cookie del navegador del usuario, pero no invalida el session ID en el servidor. ¿Cuál es el riesgo?

- a) El usuario tendrá que iniciar sesión dos veces la próxima vez
- b) Un atacante con una copia previa del session ID puede seguir usándolo para acceder al sistema
- c) La base de datos quedará con registros de sesión corruptos
- d) El servidor dejará de funcionar correctamente después del logout

**Respuesta: [ b ]**

---

**Pregunta 6** *(Intermedia)*
¿Qué ataque específico previene el atributo `SameSite=Strict` en una cookie?

- a) SQL Injection en formularios de autenticación
- b) XSS (Cross-Site Scripting) en páginas dinámicas
- c) CSRF (Cross-Site Request Forgery) desde dominios externos
- d) Brute force en el formulario de login

**Respuesta: [ c ]**

---

**Pregunta 7** *(Intermedia)*
En el ataque Session Fixation, el atacante:

- a) Adivina el session ID del usuario usando fuerza bruta
- b) Fuerza al usuario a utilizar un session ID ya conocido por el atacante antes de autenticarse
- c) Inyecta código JavaScript para robar la cookie del usuario
- d) Intercepta el tráfico de red para capturar el session ID

**Respuesta: [ b]**

---

**Pregunta 8** *(Avanzada)*
En RBAC (Role-Based Access Control), el Principio de Mínimo Privilegio establece que:

- a) Los administradores deben tener acceso a todos los recursos para gestionar el sistema
- b) Cada usuario debe tener únicamente los permisos estrictamente necesarios para su función
- c) Los permisos se asignan individualmente a cada usuario según su antigüedad
- d) Los roles deben definirse con el máximo de permisos posibles para no limitar la productividad

**Respuesta: [ b ]**

---

**Pregunta 9** *(Avanzada)*
Un sistema lee el rol del usuario desde el campo oculto del formulario HTML: `<input type="hidden" name="role" value="usuario">`. ¿Cuál es la vulnerabilidad?

- a) Inyección SQL, porque el campo contiene texto sin parametrizar
- b) Parameter tampering — el usuario puede editar el campo con DevTools y darse el rol "admin"
- c) CSRF, porque el formulario puede ser enviado desde otro dominio
- d) Session Fixation, porque el role está en el cliente antes de la autenticación

**Respuesta: [ b ]**

---

**Pregunta 10** *(Avanzada)*
Un navegador moderno recibe `Set-Cookie: session=abc; SameSite=None` sin el atributo `Secure`. ¿Qué ocurre?

- a) El navegador acepta la cookie y la envía en todas las peticiones
- b) El navegador rechaza y descarta la cookie automáticamente
- c) El navegador convierte la cookie a SameSite=Lax automáticamente
- d) La cookie funciona normalmente pero genera una advertencia en la consola

**Respuesta: [ b ]**

---

## SECCIÓN B — COMPLETAR Y RELACIONAR (20 puntos)

### B1 — Completar espacios en blanco (10 puntos — 2 pts c/u)

Usa las palabras del banco: `HttpOnly` / `session.clear()` / `servidor` / `Secure` / `RBAC` / `SameSite` / `session_id` / `stateless`

1. HTTP es un protocolo stateless porque no recuerda peticiones anteriores entre cliente y servidor.

2. El atributo Secure garantiza que la cookie de sesión no sea transmitida sobre conexiones HTTP no cifradas.

3. El modelo de control de acceso RBAC asigna permisos a través de roles, no directamente a usuarios individuales.

4. En un logout correcto, además de eliminar la cookie del cliente, el servidor debe invalidar el session ID en su propio almacén.

5. Para prevenir Session Fixation, después de una autenticación exitosa se debe ejecutar session.clear() para limpiar la sesión previa.

---

### B2 — Relacionar columnas (10 puntos)

Relaciona cada atributo/concepto (columna A) con su descripción correcta (columna B).

| Columna A | | Columna B |
|---|---|---|
| 1. `HttpOnly` -> c | a) Controla si la cookie se envía en peticiones cross-site |
| 2. `Secure` -> f| b) El servidor no puede recordar peticiones anteriores |
| 3. `SameSite=Lax` -> a_____ | c) Previene que JavaScript lea el valor de la cookie |
| 4. Session Hijacking-> e _____ | d) El atacante forza un session ID conocido antes del login |
| 5. Session Fixation -> d _____ | e) Robo de un session ID válido para suplantar al usuario |
| 6. Stateless -> b _____ | f) La cookie solo viaja sobre conexiones HTTPS |
| 7. Mínimo Privilegio -> h _____ | g) Conjunto de permisos asignados a un tipo de usuario |
| 8. Rol -> g _____ | h) Cada usuario tiene solo los permisos que necesita |

---

## SECCIÓN C — ANÁLISIS Y REFLEXIÓN (30 puntos)

*Responde con párrafos completos de 3-5 líneas. No uses listas en esta sección.*

---

**Pregunta C1 (10 puntos)**
Un compañero propone guardar el session ID del usuario en `localStorage` porque "es más fácil acceder a él desde JavaScript". Explica por qué esta decisión es un riesgo de seguridad y cuál sería la alternativa correcta con sus fundamentos técnicos.

*Tu respuesta:*

Guardar el session ID en localStorage no es seguro porque puede ser leído por código JavaScript ejecutado en el navegador. Si la página tuviera una vulnerabilidad XSS, un atacante podría capturar ese identificador y usarlo para entrar como si fuera el usuario legítimo. La opción más segura es usar una cookie de sesión con atributos como HttpOnly, Secure y SameSite. De esa forma, JavaScript no puede leer la cookie, esta solo viaja por HTTPS y se reduce el riesgo de ataques CSRF.

---

**Pregunta C2 (10 puntos)**
Compara el ataque **Session Hijacking** con el ataque **Session Fixation**: en qué se diferencian en su mecánica, qué tienen en común en su objetivo final, y cuál es la medida técnica específica que previene cada uno.

*Tu respuesta:*

El Session Hijacking ocurre cuando un atacante roba una sesión válida que ya fue iniciada por el usuario, por ejemplo mediante XSS o interceptación de tráfico. En cambio, el Session Fixation sucede cuando el atacante logra que la víctima use un identificador de sesión conocido antes de iniciar sesión. Ambos ataques buscan lo mismo: suplantar al usuario sin conocer su contraseña. Para prevenir hijacking se deben proteger las cookies con HttpOnly, Secure y HTTPS; para prevenir fixation se debe regenerar o limpiar la sesión después del login, por ejemplo usando session.clear().

---

**Mini caso de análisis — Para preguntas C3a y C3b**

> El equipo de desarrollo de **RetailFácil** (una tienda online peruana) implementó el siguiente sistema de autenticación:
>
> - Al hacer login, el servidor crea una cookie: `Set-Cookie: uid=456; role=comprador; Path=/`
> - Los precios se envían como campos ocultos en el formulario: `<input type="hidden" name="precio" value="299.00">`
> - Al hacer clic en "pagar", el backend lee `request.form['precio']` y procesa ese valor como el precio real
> - La sesión no tiene tiempo de expiración configurado

**Pregunta C3a (5 puntos)**
Identifica los problemas de seguridad presentes en el diseño de RetailFácil y explica cómo cada uno podría ser explotado por un atacante.

*Tu respuesta:*

El diseño de RetailFácil presenta varios problemas porque confía en datos enviados desde el cliente. El rol y el uid están dentro de una cookie que el usuario podría modificar desde el navegador, cambiando por ejemplo su rol de comprador a administrador. También es inseguro enviar el precio como campo oculto, porque un atacante puede modificarlo antes de pagar. Además, al no existir tiempo de expiración, una sesión robada podría seguir siendo utilizada por mucho tiempo.

**Pregunta C3b (5 puntos)**
Propón cómo debería reimplementarse este sistema de manera segura, explicando el principio de seguridad que aplica en cada corrección.

*Tu respuesta:*

El sistema debería guardar el rol y el identificador del usuario del lado del servidor, no en una cookie manipulable por el cliente. El precio del producto debe obtenerse desde la base de datos según el producto seleccionado, ignorando cualquier precio enviado desde el formulario. También se debe configurar la cookie con HttpOnly, Secure y SameSite, además de establecer un tiempo de expiración de sesión. Estas correcciones aplican el principio de no confiar en el cliente y el principio de mínimo privilegio.

---

## SECCIÓN D — PREGUNTAS AVANZADAS Y DE CASO (30 puntos)

---

### Caso profesional (15 puntos)

> **SaludNet Perú** es una startup de telemedicina que permite a pacientes ver sus resultados de laboratorio y a médicos acceder a historias clínicas. El sistema usa una cookie de sesión sin `HttpOnly` ni `Secure`. El sistema tiene tres tipos de usuarios: paciente, médico y administrador.
>
> Un auditor de seguridad detectó que un médico puede acceder a la historia clínica de cualquier paciente simplemente cambiando el parámetro en la URL: `/historia?paciente_id=1023` → `/historia?paciente_id=1024`. También encontró que la cookie de sesión puede leerse con JavaScript y que el sistema funciona sobre HTTP sin redirigir a HTTPS.

**Pregunta D1 (5 puntos)**
¿Qué vulnerabilidades del OWASP Top 10 están presentes en SaludNet Perú? Nómbralas por su código y nombre, y explica brevemente cómo se manifiesta cada una en el caso.

*Tu respuesta:*

En SaludNet Perú se observa A01:2021 Broken Access Control, porque un médico puede acceder a historias clínicas de pacientes no asignados cambiando el parámetro paciente_id en la URL. También aparece A02:2021 Cryptographic Failures, ya que el sistema funciona por HTTP y expone información sensible sin cifrado. Además, existe A07:2021 Identification and Authentication Failures, porque la cookie de sesión no tiene HttpOnly ni Secure, permitiendo que pueda ser robada o reutilizada. También puede considerarse una mala configuración de seguridad por no forzar HTTPS.


**Pregunta D2 (5 puntos)**
Diseña el esquema RBAC completo para SaludNet Perú: define los roles necesarios y los permisos específicos de cada uno. Luego escribe el pseudocódigo o código Python del decorador que verificaría el acceso antes de mostrar una historia clínica.

*Tu respuesta:*

El sistema debe manejar tres roles principales. El paciente solo debe acceder a sus propios resultados e historia clínica. El médico debe acceder únicamente a los pacientes que tiene asignados. El administrador puede gestionar usuarios y configuraciones, pero el acceso a historias clínicas debe estar controlado y auditado. El decorador debe validar tanto el rol como la relación entre el usuario y el recurso solicitado.

```python
from functools import wraps
from flask import session, redirect, url_for, abort


def require_historia_access(f):
    """
    Decorador que controla el acceso a la historia clínica de un paciente.

    Reglas:
    - Paciente: solo puede acceder a su propia historia clínica.
    - Médico: solo puede acceder a las historias de sus pacientes asignados.
    - Administrador: acceso total.
    """

    @wraps(f)
    def inner(paciente_id, *args, **kwargs):
        # Verificar autenticación
        if "user_id" not in session:
            return redirect(url_for("login"))

        rol = session.get("user_role")
        user_id = session.get("user_id")

        # Paciente: solo puede acceder a su propia historia
        if rol == "paciente":
            if str(paciente_id) != str(user_id):
                abort(403)

        # Médico: solo puede acceder a pacientes asignados
        elif rol == "medico":
            if paciente_id not in pacientes_asignados(user_id):
                abort(403)

        # Administrador: acceso completo
        elif rol == "admin":
            pass

        # Cualquier otro rol no tiene acceso
        else:
            abort(403)

        return f(paciente_id, *args, **kwargs)

    return inner
---

**Pregunta D3 (5 puntos)**
Si un médico puede leer la cookie de sesión de un paciente mediante una vulnerabilidad XSS, ¿cómo puede un atacante usar esa cookie para acceder al sistema como ese paciente? Describe el ataque paso a paso y qué atributo de cookie lo hubiera prevenido.

*Tu respuesta:*

Si existe una vulnerabilidad XSS y la cookie no tiene HttpOnly, un atacante puede ejecutar JavaScript para leer la cookie del paciente mediante document.cookie. Luego puede enviar ese valor a un servidor externo o copiarlo para usarlo en su propio navegador. Al colocar esa cookie robada, el sistema podría reconocer al atacante como si fuera el paciente autenticado. El atributo que ayuda a prevenir este robo es HttpOnly, porque impide que JavaScript lea el valor de la cookie.

---

**Pregunta D4 — Diseño y propuesta (8 puntos)**
> "¿Cómo implementarías la gestión de sesiones para un sistema bancario en Flask que debe cumplir estos requisitos: sesión que expira a los 15 minutos de inactividad, cookie segura contra XSS y CSRF, logout que invalide la sesión en el servidor, y RBAC con roles cliente/operador/admin?"

Escribe el código Python/Flask completo que implementa esa gestión. Comenta cada decisión de seguridad.

*Tu código:*

```python
from flask import Flask, session, redirect, url_for, request, abort
from functools import wraps
from datetime import datetime, timedelta
import secrets

app = Flask(__name__)

# Clave secreta fuerte para firmar la sesión
app.config["SECRET_KEY"] = secrets.token_hex(32)

# Configuración segura de cookies
app.config.update(
    SESSION_COOKIE_HTTPONLY=True,      # Evita que JavaScript lea la cookie
    SESSION_COOKIE_SECURE=True,        # Solo se envía por HTTPS
    SESSION_COOKIE_SAMESITE="Strict",  # Reduce el riesgo de CSRF
    PERMANENT_SESSION_LIFETIME=timedelta(minutes=15),
)


def login_user(user_id, role):
    """
    Inicia sesión de forma segura.
    Previene Session Fixation limpiando cualquier sesión previa.
    """
    session.clear()

    session["user_id"] = user_id
    session["user_role"] = role
    session["last_activity"] = datetime.utcnow().isoformat()
    session.permanent = True


@app.before_request
def verificar_inactividad():
    """
    Verifica el tiempo de inactividad del usuario.
    Si supera los 15 minutos, invalida la sesión.
    """
    if "user_id" in session:
        ultima_actividad = session.get("last_activity")

        if ultima_actividad:
            tiempo_inactivo = (
                datetime.utcnow() - datetime.fromisoformat(ultima_actividad)
            )

            if tiempo_inactivo > timedelta(minutes=15):
                session.clear()
                return redirect(url_for("login"))

        # Actualiza la última actividad
        session["last_activity"] = datetime.utcnow().isoformat()


def require_role(*roles):
    """
    Decorador para restringir acceso según el rol del usuario.
    """

    def decorator(f):
        @wraps(f)
        def inner(*args, **kwargs):
            # Verifica autenticación
            if "user_id" not in session:
                return redirect(url_for("login"))

            # Verifica autorización
            if session.get("user_role") not in roles:
                abort(403)

            return f(*args, **kwargs)

        return inner

    return decorator


@app.route("/logout")
def logout():
    """
    Cierra la sesión eliminando toda la información almacenada.
    """
    session.clear()
    return redirect(url_for("login"))


@app.route("/cliente")
@require_role("cliente")
def panel_cliente():
    return "Panel del cliente"


@app.route("/operador")
@require_role("operador", "admin")
def panel_operador():
    return "Panel del operador"


@app.route("/admin")
@require_role("admin")
def panel_admin():
    return "Panel del administrador"


if __name__ == "__main__":
    app.run(debug=True)

---

**Pregunta D5 — Pensamiento crítico (7 puntos)**
> "¿Qué pasaría si un sistema implementa HttpOnly y Secure en las cookies, pero guarda el session ID con baja entropía (ej: un número secuencial como session_id=1001, 1002, 1003...)?"

Explica el tipo de ataque que esto habilitaría, cómo lo ejecutaría un atacante, y cuál es el estándar correcto para generar session IDs seguros.

*Tu respuesta:*

Aunque una cookie tenga HttpOnly y Secure, el sistema sigue siendo vulnerable si el identificador de sesión es fácil de adivinar. Si los valores son secuenciales, como 1001, 1002 o 1003, un atacante podría probar números cercanos hasta encontrar una sesión activa. Este ataque se conoce como predicción de sesión o session prediction. La forma correcta de evitarlo es generar identificadores con alta entropía usando generadores criptográficamente seguros, de modo que sean aleatorios, largos y prácticamente imposibles de adivinar.

---

*Universidad Autónoma del Perú — DD281 Programación Segura — Semana 3 — 2026-1*
