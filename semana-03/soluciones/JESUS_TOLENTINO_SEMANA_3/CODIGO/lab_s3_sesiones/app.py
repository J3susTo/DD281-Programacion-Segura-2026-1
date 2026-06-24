from flask import Flask, session, redirect, url_for, request, render_template, abort, make_response
from functools import wraps
from datetime import timedelta, datetime
import secrets

app = Flask(__name__)

app.config["SECRET_KEY"] = secrets.token_hex(32)

app.config.update(
    SESSION_COOKIE_HTTPONLY=True,
    SESSION_COOKIE_SECURE=False,
    SESSION_COOKIE_SAMESITE="Lax",
    PERMANENT_SESSION_LIFETIME=timedelta(minutes=30)
)

USUARIOS = {
    "admin@test.com": {
        "password": "Admin2026!",
        "role": "admin",
        "nombre": "Juan Admin"
    },
    "supervisor@test.com": {
        "password": "Super2026!",
        "role": "supervisor",
        "nombre": "Bruno Sup"
    },
    "usuario@test.com": {
        "password": "Usuario2026!",
        "role": "usuario",
        "nombre": "Gian User"
    },
}

sesiones_auditoria = {}


def require_role(*roles):
    def decorator(f):
        @wraps(f)
        def inner(*args, **kwargs):
            if "user_id" not in session:
                return redirect(url_for("login"))

            if session.get("user_role") not in roles:
                return render_template(
                    "error.html",
                    code=403,
                    msg=f"Acceso denegado. Requiere uno de: {roles}"
                ), 403

            email = session.get("user_id")
            if email in sesiones_auditoria:
                sesiones_auditoria[email]["last_seen"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

            return f(*args, **kwargs)
        return inner
    return decorator


@app.route("/")
def home():
    if "user_id" in session:
        return redirect(url_for("dashboard"))
    return redirect(url_for("login"))


@app.route("/login", methods=["GET", "POST"])
def login():
    if request.method == "POST":
        email = request.form.get("email", "").strip()
        password = request.form.get("password", "")

        user = USUARIOS.get(email)

        if user and user["password"] == password:
            session.clear()

            session["user_id"] = email
            session["user_role"] = user["role"]
            session["user_name"] = user["nombre"]
            session.permanent = True

            ahora = datetime.now().strftime("%Y-%m-%d %H:%M:%S")

            sesiones_auditoria[email] = {
                "usuario": user["nombre"],
                "rol": user["role"],
                "ip": request.remote_addr,
                "login_at": ahora,
                "last_seen": ahora
            }

            return redirect(url_for("dashboard"))

        return render_template("login.html", error="Credenciales incorrectas")

    return render_template("login.html")


@app.route("/dashboard")
def dashboard():
    if "user_id" not in session:
        return redirect(url_for("login"))

    return render_template(
        "dashboard.html",
        name=session["user_name"],
        role=session["user_role"]
    )


@app.route("/logout")
def logout():
    sesiones_auditoria.pop(session.get("user_id"), None)
    session.clear()

    response = make_response(redirect(url_for("login")))
    response.delete_cookie("session")

    return response


@app.route("/mi-perfil")
@require_role("admin", "supervisor", "usuario")
def mi_perfil():
    return render_template(
        "dashboard.html",
        name=session["user_name"],
        role=session["user_role"],
        seccion="Mi Perfil"
    )


@app.route("/reportes")
@require_role("admin", "supervisor")
def ver_reportes():
    reportes = [
        {"titulo": "Reporte de Accesos", "fecha": "2026-06-15"},
        {"titulo": "Reporte de Sesiones", "fecha": "2026-06-14"},
        {"titulo": "Reporte de Incidentes", "fecha": "2026-06-13"},
    ]

    return render_template(
        "dashboard.html",
        name=session["user_name"],
        role=session["user_role"],
        seccion="Reportes",
        reportes=reportes
    )


@app.route("/admin/panel")
@require_role("admin")
def panel_admin():
    return render_template(
        "admin.html",
        name=session["user_name"],
        role=session["user_role"],
        usuarios=USUARIOS,
        sesiones=sesiones_auditoria
    )


@app.route("/admin/usuarios/<email>/eliminar", methods=["POST"])
@require_role("admin")
def eliminar_usuario(email):
    if email == session.get("user_id"):
        return render_template(
            "error.html",
            code=403,
            msg="No puedes eliminar tu propia cuenta."
        ), 403

    if email not in USUARIOS:
        return render_template(
            "error.html",
            code=404,
            msg="El usuario no existe."
        ), 404

    del USUARIOS[email]
    sesiones_auditoria.pop(email, None)

    return redirect(url_for("panel_admin"))


@app.route("/demo/fixation")
def demo_fixation():
    session_id_antes = request.cookies.get("session", "Sin sesión previa")

    info = {
        "session_id_antes_login": session_id_antes[:20] + "..." if len(session_id_antes) > 20 else session_id_antes,
        "tiene_user_id": "user_id" in session,
        "nota": "Después del login, el session_id debería cambiar completamente."
    }

    return str(info)


@app.route("/admin/sesiones-activas")
@require_role("admin")
def sesiones_activas():
    filas = "".join(
        f"<tr>"
        f"<td>{email}</td>"
        f"<td>{data['usuario']}</td>"
        f"<td>{data['rol']}</td>"
        f"<td>{data['ip']}</td>"
        f"<td>{data['login_at']}</td>"
        f"<td>{data['last_seen']}</td>"
        f"</tr>"
        for email, data in sesiones_auditoria.items()
    )

    return (
        "<h2>Sesiones activas</h2>"
        "<table border='1' cellpadding='6'>"
        "<tr>"
        "<th>Email</th>"
        "<th>Usuario</th>"
        "<th>Rol</th>"
        "<th>IP</th>"
        "<th>Login</th>"
        "<th>Último acceso</th>"
        "</tr>"
        f"{filas}"
        "</table>"
        "<br><a href='/dashboard'>Volver</a>"
    )


if __name__ == "__main__":
    app.run(debug=True, port=5000)