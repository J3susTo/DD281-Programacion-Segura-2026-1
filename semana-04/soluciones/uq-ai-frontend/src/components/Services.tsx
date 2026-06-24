const services = [
  {
    icon: "🤖",
    title: "Agentes de IA",
    desc: "Automatiza procesos con agentes inteligentes",
  },
  {
    icon: "💬",
    title: "Chatbots Empresariales",
    desc: "Atención al cliente 24/7 con IA",
  },
  {
    icon: "⚡",
    title: "Automatización",
    desc: "Elimina tareas repetitivas con RPA + IA",
  },
  {
    icon: "🏥",
    title: "Salud & Educación",
    desc: "Asistentes especializados para tu sector",
  },
  {
    icon: "📊",
    title: "Big Data",
    desc: "Análisis de datos a escala empresarial",
  },
  { icon: "🔒", title: "DevSecOps", desc: "Seguridad integrada en cada etapa" },
];

export default function Services() {
  return (
    <section id="servicios" className="py-20 bg-gray-900">
      <div className="max-w-7xl mx-auto px-4">
        <h2 className="text-4xl font-bold text-center text-white mb-4">
          UQ AI Solutions
        </h2>
        <p className="text-gray-400 text-center mb-12">
          Soluciones de IA para empresas de todos los tamaños
        </p>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {services.map((s, i) => (
            <div
              key={i}
              className="bg-gray-800 rounded-xl p-6 hover:bg-gray-700 transition-all border border-gray-700 hover:border-blue-500 cursor-pointer"
            >
              <div className="text-4xl mb-3">{s.icon}</div>
              <h3 className="text-xl font-semibold text-white mb-2">
                {s.title}
              </h3>
              <p className="text-gray-400">{s.desc}</p>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
