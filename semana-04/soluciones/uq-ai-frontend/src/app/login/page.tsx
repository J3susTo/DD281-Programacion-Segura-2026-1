"use client";
import { useState } from "react";
import axios from "axios";
import Cookies from "js-cookie";
import { useRouter } from "next/navigation";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", {
        email,
        password,
      });
      // Guardamos el token en una cookie segura
      Cookies.set("token", res.data.token);
      alert("¡Bienvenido! Has iniciado sesión correctamente.");
      router.push("/");
    } catch {
      alert("Credenciales incorrectas.");
    }
  };

  return (
    <div className="min-h-screen bg-gray-950 flex items-center justify-center p-4">
      <form
        onSubmit={handleLogin}
        className="bg-gray-900 p-8 rounded-xl border border-gray-700 w-full max-w-md"
      >
        <h2 className="text-2xl font-bold text-white mb-6 text-center">
          Acceso Administrativo
        </h2>
        <input
          type="email"
          placeholder="Email"
          required
          onChange={(e) => setEmail(e.target.value)}
          className="w-full p-3 mb-4 bg-gray-800 text-white rounded border border-gray-700"
        />
        <input
          type="password"
          placeholder="Contraseña"
          required
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-3 mb-6 bg-gray-800 text-white rounded border border-gray-700"
        />
        <button
          type="submit"
          className="w-full p-3 bg-blue-600 text-white font-bold rounded hover:bg-blue-500"
        >
          Ingresar
        </button>
      </form>
    </div>
  );
}
