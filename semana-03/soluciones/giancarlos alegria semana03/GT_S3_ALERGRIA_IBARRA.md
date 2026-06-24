# S3 — GUÍA DE TRABAJO DEL ESTUDIANTE
## Autenticación, Gestión de Cookies y Niveles de Acceso

| Campo | Detalle |
|---|---|
| **Curso** | Programación Segura (DD281) — Semana 3 |
| **Nombre del estudiante** | ALEGRIA IBARRA GIANCARLOS |
| **Código** | 2221895512 |
| **Fecha de entrega** | 20/06/2026 |
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

**Respuesta: [ c ]**

---

**Pregunta 3** *(Básica)*
El atributo `HttpOnly` en una cookie:

- a) Garantiza que la cookie solo se transmita por HTTPS
- b) Impide que JavaScript del navegador pueda leer el valor de la cookie
- c) Limita la cookie a peticiones del mismo dominio únicamente
- d) Establece la fecha de expiración automática de la cookie

**Respuesta: [ b ]**

---

**Pregunta 4** *(Básica)*
El atributo `Secure` en una cookie garantiza que:

- a) La cookie no puede ser modificada por el usuario
- b) La cookie solo se transmite sobre conexiones HTTPS, nunca HTTP
- c) JavaScript no puede acceder al valor de la cookie
- d) La cookie expira automáticamente al cerrar el navegador

**Respuesta: [ b ]**

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

**Respuesta: [ b ]**

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

Banco: `HttpOnly` / `session.clear()` / `servidor` / `Secure` / `RBAC` / `SameSite` / `session_id` / `stateless`

1. HTTP es un protocolo **stateless** porque no recuerda peticiones anteriores entre cliente y servidor.

2. El atributo **Secure** garantiza que la cookie de sesión no sea transmitida sobre conexiones HTTP no cifradas.

3. El modelo de control de acceso **RBAC** asigna permisos a través de roles, no directamente a usuarios individuales.

4. En un logout correcto, además de eliminar la cookie del cliente, el **servidor** debe invalidar el session ID en su propio almacén.

5. Para prevenir Session Fixation, después de una autenticación exitosa se debe ejecutar **session.clear()** para limpiar la sesión previa.

---

### B2 — Relacionar columnas (10 puntos)

| Columna A | | Columna B |
|---|---|---|
| 1. `HttpOnly` | **c** | a) Controla si la cookie se envía en peticiones cross-site |
| 2. `Secure` | **f** | b) El servidor no puede recordar peticiones anteriores |
| 3. `SameSite=Lax` | **a** | c) Previene que JavaScript lea el valor de la cookie |
| 4. Session Hijacking | **e** | d) El atacante forza un session ID conocido antes del login |
| 5. Session Fixation | **d** | e) Robo de un session ID válido para suplantar al usuario |
| 6. Stateless | **b** | f) La cookie solo viaja sobre conexiones HTTPS |
| 7. Mínimo Privilegio | **h** | g) Conjunto de permisos asignados a un tipo de usuario |
| 8. Rol | **g** | h) Cada usuario tiene solo los permisos que necesita |

**Resumen:** 1→c, 2→f, 3→a, 4→e, 5→d, 6→b, 7→h, 8→g

---

## SECCIÓN C — ANÁLISIS Y REFLEXIÓN (30 puntos)

*Responde con párrafos completos de 3-5 líneas. No uses listas en esta sección.*

---

**Pregunta C1 (10 puntos)**

*Tu respuesta:*

Guardar el Session ID en localStorage representa un riesgo de seguridad porque cualquier script que se ejecute en la página puede acceder a ese valor mediante localStorage.getItem(). Si la aplicación sufre una vulnerabilidad XSS (Cross-Site Scripting), un atacante podría obtener el identificador de sesión y secuestrar la cuenta del usuario.

Además, localStorage no dispone de mecanismos de protección nativos como HttpOnly, Secure o SameSite, y su contenido permanece almacenado incluso después de cerrar el navegador o la pestaña.

La práctica recomendada es almacenar el Session ID en una cookie segura configurada con los atributos HttpOnly, para impedir el acceso desde JavaScript; Secure, para que solo se transmita a través de conexiones HTTPS; y SameSite, para reducir el riesgo de ataques CSRF. De esta forma, la gestión de la sesión queda protegida por el navegador y el servidor, minimizando la exposición del identificador frente a posibles ataques del lado del cliente.

---

**Pregunta C2 (10 puntos)**

*Tu respuesta:*

Aunque ambos ataques buscan secuestrar la sesión de un usuario, su funcionamiento es diferente. En el Session Hijacking, el atacante obtiene un Session ID válido que ya está siendo utilizado por la víctima, generalmente mediante técnicas como XSS, interceptación de tráfico o acceso al dispositivo. Una vez obtenido, utiliza ese identificador para hacerse pasar por el usuario autenticado.

Por otro lado, en el Session Fixation, el atacante no roba una sesión existente, sino que fuerza a la víctima a utilizar un Session ID previamente conocido por él. Si la víctima inicia sesión utilizando ese identificador, el atacante podrá reutilizarlo posteriormente para acceder a la cuenta.

La principal diferencia radica en el momento en que el atacante obtiene el identificador de sesión: en el hijacking lo roba después de la autenticación, mientras que en la fixation lo conoce antes de que la víctima inicie sesión.

Para mitigar el Session Hijacking, es recomendable proteger las cookies de sesión mediante los atributos HttpOnly, Secure y el uso de HTTPS/TLS, reduciendo las posibilidades de robo del identificador. En cambio, para prevenir el Session Fixation, la medida más importante es regenerar el Session ID después de una autenticación exitosa, invalidando cualquier identificador previo que pudiera haber sido fijado por un atacante.

---

**Mini caso de análisis — RetailFácil**

**Pregunta C3a (5 puntos)**

*Tu respuesta:*

El diseño presenta varias vulnerabilidades de seguridad. En primer lugar, almacenar el **rol** y el **identificador del usuario (uid)** en una cookie sin protección permite que cualquier usuario modifique estos valores desde las herramientas del navegador. Como consecuencia, un atacante podría alterar el contenido de la cookie, por ejemplo cambiando su rol a administrador o suplantando la identidad de otro usuario, lo que constituye un caso de **Parameter Tampering**.

Asimismo, el hecho de que el **precio del producto** se envíe mediante un campo oculto y sea aceptado directamente por el servidor representa un riesgo importante. Un usuario malintencionado podría modificar dicho valor antes de enviar la solicitud, reduciendo artificialmente el monto a pagar y comprometiendo la integridad de la transacción.

Finalmente, la **falta de un mecanismo de expiración de sesión** provoca que las cookies permanezcan válidas durante un tiempo indefinido. Esto incrementa el riesgo de accesos no autorizados, ya que una cookie robada, filtrada o abandonada podría ser reutilizada para secuestrar la sesión de un usuario legítimo.

**Pregunta C3b (5 puntos)**

*Tu respuesta:*

Para mitigar estas vulnerabilidades, la información sensible como el **rol** y el **identificador del usuario (uid)** debe almacenarse exclusivamente en el servidor, dentro de la sesión de la aplicación, y no en cookies que puedan ser modificadas por el cliente. Este enfoque sigue el principio de **no confiar en el cliente**, dejando la autoridad y la validación de los datos críticos bajo control del servidor.

De igual forma, el **precio de los productos** no debe ser recibido ni aceptado directamente desde el formulario. En su lugar, el servidor debe obtener y validar el precio a partir del identificador del producto consultado en la base de datos, ignorando cualquier valor proporcionado por el usuario. Esto garantiza la integridad de las transacciones y aplica el principio de **validación del lado del servidor**.

Además, la cookie de sesión debe configurarse con los atributos **HttpOnly**, **Secure** y **SameSite**, con el fin de proteger el token de sesión frente a ataques como XSS, interceptación de tráfico y CSRF. Finalmente, es recomendable establecer un **tiempo de expiración de sesión (session timeout)** para reducir el período durante el cual una sesión comprometida podría ser reutilizada por un atacante.


---

## SECCIÓN D — PREGUNTAS AVANZADAS Y DE CASO (30 puntos)

### Caso profesional — SaludNet Perú (15 puntos)

**Pregunta D1 (5 puntos)**

*Tu respuesta:*

El escenario presenta varias vulnerabilidades alineadas con el **OWASP Top 10 2021**. En primer lugar, se identifica **A01:2021 – Broken Access Control**, específicamente un caso de **Insecure Direct Object Reference (IDOR)**. Esto ocurre cuando un médico puede modificar el parámetro `paciente_id` en la URL para acceder a historias clínicas de pacientes que no tiene autorización para consultar, debido a que el servidor no valida adecuadamente los permisos sobre el recurso solicitado.

Asimismo, se evidencia **A02:2021 – Cryptographic Failures**, ya que la aplicación transmite información sensible a través de **HTTP sin cifrado** y utiliza cookies sin el atributo **Secure**. Esta situación expone datos médicos confidenciales a posibles interceptaciones durante la transmisión.

También está presente **A07:2021 – Identification and Authentication Failures**, debido a que la cookie de sesión carece de atributos de protección como **HttpOnly** y **Secure**. Como consecuencia, un atacante podría obtener el identificador de sesión mediante ataques XSS o interceptación de tráfico, logrando la suplantación de usuarios legítimos.

Finalmente, se observa **A05:2021 – Security Misconfiguration**, reflejada en la falta de redirección obligatoria de HTTP a HTTPS y en la configuración insegura de las cookies de sesión. Estas deficiencias aumentan la superficie de ataque y facilitan la explotación de otras vulnerabilidades presentes en el sistema.


**Pregunta D2 (5 puntos)**

*Tu respuesta:*

El sistema debe implementar un esquema de control de acceso basado en roles (**RBAC**) que limite las acciones de cada usuario según sus responsabilidades. El **paciente** únicamente debe tener acceso a sus propios resultados de laboratorio, impidiendo la consulta de información perteneciente a otros usuarios. El **médico** solo puede visualizar las historias clínicas de los pacientes que le han sido asignados, mientras que el **administrador** se encarga de la gestión de usuarios y la configuración del sistema, sin acceso directo a las historias clínicas, salvo en procesos de auditoría debidamente autorizados.

Sin embargo, la validación no debe basarse únicamente en el rol del usuario. Para evitar vulnerabilidades como **IDOR (Insecure Direct Object Reference)**, es necesario verificar también la **propiedad o autorización sobre el recurso solicitado**. Esto significa que, además de confirmar que el usuario posee un rol válido para realizar una acción, el sistema debe comprobar que el recurso al que intenta acceder le pertenece o se encuentra dentro de los recursos autorizados para dicho usuario. De esta forma se garantiza un control de acceso más robusto y se evita que la simple manipulación de identificadores permita acceder a información sensible.


```python
from functools import wraps
from flask import session, redirect, url_for, abort

def require_acceso_historia(f):
    @wraps(f)
    def inner(paciente_id, *args, **kwargs):
        # 1. ¿Autenticado?
        if 'user_id' not in session:
            return redirect(url_for('login'))
        rol = session.get('user_role')
        uid = session.get('user_id')
        # 2. Autorización por rol + propiedad del recurso
        if rol == 'paciente' and paciente_id != uid:
            abort(403)                      # un paciente solo ve lo suyo
        elif rol == 'medico' and paciente_id not in pacientes_asignados(uid):
            abort(403)                      # médico solo ve sus pacientes
        elif rol not in ('paciente', 'medico', 'admin'):
            abort(403)
        return f(paciente_id, *args, **kwargs)
    return inner
```

**Pregunta D3 (5 puntos)**

*Tu respuesta:*

La ausencia del atributo **HttpOnly** en la cookie de sesión permite que cualquier script ejecutado en la página pueda acceder a su contenido mediante `document.cookie`. Si la aplicación presenta una vulnerabilidad **XSS (Cross-Site Scripting)**, un atacante podría ejecutar código malicioso capaz de obtener el identificador de sesión y enviarlo a un servidor bajo su control.

Una vez obtenido el **Session ID**, el atacante puede incorporarlo a su propio navegador y realizar solicitudes al sistema haciéndose pasar por el usuario legítimo. Debido a que el servidor identifica a los usuarios mediante dicho identificador, aceptará las peticiones como si provinieran de la víctima autenticada, produciéndose un caso de **Session Hijacking** o secuestro de sesión.

Esta situación podría haberse mitigado mediante el uso del atributo **HttpOnly**, que impide el acceso a las cookies desde JavaScript, dificultando el robo del token de sesión a través de ataques XSS. Adicionalmente, el atributo **Secure** garantiza que la cookie solo sea transmitida a través de conexiones HTTPS, reduciendo el riesgo de interceptación durante el tránsito de los datos.

---

**Pregunta D4 — Diseño y propuesta (8 puntos)**

*Tu código:*

```python
# Gestión de sesiones para un sistema bancario en Flask
from flask import Flask, session, redirect, url_for, request, abort
from functools import wraps
from datetime import timedelta, datetime
import secrets

app = Flask(__name__)

# Clave de firma de la cookie de sesión: aleatoria y de alta entropía
app.config['SECRET_KEY'] = secrets.token_hex(32)

# Cookie segura contra XSS y CSRF
app.config.update(
    SESSION_COOKIE_HTTPONLY=True,     # JS no puede leer la cookie -> mitiga robo por XSS
    SESSION_COOKIE_SECURE=True,       # la cookie solo viaja por HTTPS
    SESSION_COOKIE_SAMESITE='Strict', # no se envía en peticiones cross-site -> mitiga CSRF
)
# Expiración absoluta de la sesión
app.config['PERMANENT_SESSION_LIFETIME'] = timedelta(minutes=15)

def login_usuario(email, rol):
    session.clear()                    # previene Session Fixation: nuevo ID tras autenticar
    session['user_id']   = email
    session['user_role'] = rol
    session['last_activity'] = datetime.utcnow().isoformat()
    session.permanent = True           # aplica PERMANENT_SESSION_LIFETIME

@app.before_request
def control_inactividad():
    # Timeout de 15 min por INACTIVIDAD (sliding): si pasó el límite, cerrar sesión
    if 'user_id' in session:
        ultima = session.get('last_activity')
        if ultima:
            inactivo = datetime.utcnow() - datetime.fromisoformat(ultima)
            if inactivo > timedelta(minutes=15):
                session.clear()
                return redirect(url_for('login'))
        session['last_activity'] = datetime.utcnow().isoformat()  # renovar actividad

def require_role(*roles):
    def decorator(f):
        @wraps(f)
        def inner(*args, **kwargs):
            if 'user_id' not in session:        # autenticación
                return redirect(url_for('login'))
            if session.get('user_role') not in roles:  # autorización (RBAC)
                abort(403)
            return f(*args, **kwargs)
        return inner
    return decorator

@app.route('/logout')
def logout():
    session.clear()    # invalida la sesión en el SERVIDOR (no solo borra la cookie)
    return redirect(url_for('login'))

# Ejemplos de RBAC con roles cliente / operador / admin
@app.route('/transferir')
@require_role('cliente')
def transferir(): ...

@app.route('/aprobar-operacion')
@require_role('operador', 'admin')
def aprobar(): ...

@app.route('/gestion-usuarios')
@require_role('admin')
def gestion(): ...
```

*Nota: para CSRF, además de `SameSite=Strict`, en producción se añadiría un token CSRF por formulario (p. ej. Flask-WTF) en las operaciones POST sensibles como transferencias.*

---

**Pregunta D5 — Pensamiento crítico (7 puntos)**

*Tu respuesta:*

Si bien los atributos **HttpOnly** y **Secure** ayudan a proteger las cookies de sesión frente a ataques de robo mediante XSS o transmisión insegura por HTTP, estas medidas resultan insuficientes cuando el **Session ID es predecible**. En este escenario, un atacante no necesita obtener la cookie de otro usuario, sino que puede intentar adivinar identificadores válidos a partir de un patrón conocido.

Por ejemplo, si los identificadores de sesión se generan de forma secuencial (`1001`, `1002`, `1003`, etc.), un atacante podría observar su propio Session ID, inferir la lógica de generación y probar valores cercanos hasta encontrar una sesión activa perteneciente a otro usuario. Este tipo de ataque se conoce como **Session Prediction** o **predicción de sesiones**, y puede derivar en la suplantación de identidad sin necesidad de comprometer credenciales o interceptar tráfico.

Para evitar este riesgo, los Session ID deben generarse mediante un **generador criptográficamente seguro de números aleatorios (CSPRNG)** y contar con una alta entropía que los haga impredecibles. Las recomendaciones de OWASP sugieren un mínimo de **64 bits de entropía efectiva**, siendo preferible utilizar **128 bits o más**. En Python, pueden emplearse funciones como `secrets.token_urlsafe()` o `secrets.token_hex()` para generar identificadores robustos.

Además, en aplicaciones desarrolladas con Flask, la **SECRET_KEY** utilizada para firmar las sesiones debe ser aleatoria, extensa y de alta entropía. De esta manera se garantiza la integridad de la información almacenada en la sesión y se evita que un atacante pueda falsificar o manipular su contenido.


---

*Universidad Autónoma del Perú — DD281 Programación Segura — Semana 3 — 2026-1*
