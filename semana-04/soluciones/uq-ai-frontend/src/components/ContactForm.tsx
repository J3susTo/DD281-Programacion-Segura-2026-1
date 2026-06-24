"use client";
import { useState } from "react";
import axios from "axios";

export default function ContactForm() {
  const [form, setForm] = useState({
    nombre: "",
    email: "",
    empresa: "",
    telefono: "",
    mensaje: "",
  });
  const [status, setStatus] = useState<"idle" | "loading" | "ok" | "error">(
    "idle",
  );

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setStatus("loading");
    try {
      // Llamamos a tu backend en el puerto 8080
      await axios.post("http://localhost:8080/api/leads", form);
      setStatus("ok");
      setForm({
        nombre: "",
        email: "",
        empresa: "",
        telefono: "",
        mensaje: "",
      });
    } catch {
      setStatus("error");
    }
  };

  return (
    <section id="contacto" className="py-20 bg-gray-950">
      <div className="max-w-2xl mx-auto px-4">
        <h2 className="text-4xl font-bold text-white text-center mb-10">
          ¿Listo para innovar con IA?
        </h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Nombre completo"
            required
            value={form.nombre}
            onChange={(e) => setForm({ ...form, nombre: e.target.value })}
            className="w-full px-4 py-3 bg-gray-800 text-white rounded-lg border border-gray-700 focus:border-blue-500 focus:outline-none"
          />
          <input
            type="email"
            placeholder="Email empresarial"
            required
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
            className="w-full px-4 py-3 bg-gray-800 text-white rounded-lg border border-gray-700 focus:border-blue-500 focus:outline-none"
          />
          <div className="flex gap-4">
            <input
              type="text"
              placeholder="Empresa"
              value={form.empresa}
              onChange={(e) => setForm({ ...form, empresa: e.target.value })}
              className="w-1/2 px-4 py-3 bg-gray-800 text-white rounded-lg border border-gray-700 focus:border-blue-500 focus:outline-none"
            />
            <input
              type="tel"
              placeholder="Teléfono"
              value={form.telefono}
              onChange={(e) => setForm({ ...form, telefono: e.target.value })}
              className="w-1/2 px-4 py-3 bg-gray-800 text-white rounded-lg border border-gray-700 focus:border-blue-500 focus:outline-none"
            />
          </div>
          <textarea
            placeholder="¿En qué podemos ayudarte?"
            rows={4}
            required
            value={form.mensaje}
            onChange={(e) => setForm({ ...form, mensaje: e.target.value })}
            className="w-full px-4 py-3 bg-gray-800 text-white rounded-lg border border-gray-700 focus:border-blue-500 focus:outline-none resize-none"
          />

          <button
            type="submit"
            disabled={status === "loading"}
            className="w-full py-3 bg-blue-600 hover:bg-blue-500 text-white font-bold rounded-lg transition-colors disabled:opacity-50"
          >
            {status === "loading" ? "Enviando..." : "Contactar a UQ AI"}
          </button>

          {status === "ok" && (
            <p className="text-green-400 text-center">
              ✅ Mensaje enviado con éxito. Te contactaremos pronto.
            </p>
          )}
          {status === "error" && (
            <p className="text-red-400 text-center">
              ❌ Ocurrió un error al enviar. Revisa tu conexión.
            </p>
          )}
        </form>
      </div>
    </section>
  );
}
