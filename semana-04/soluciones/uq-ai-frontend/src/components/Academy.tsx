const courses = [
  {
    title: "Machine Learning Avanzado",
    level: "Intermedio",
    duration: "8 semanas",
  },
  { title: "Sistemas RAG y LLMs", level: "Avanzado", duration: "6 semanas" },
  {
    title: "Programación Segura y DevSecOps",
    level: "Todos",
    duration: "10 semanas",
  },
];

export default function Academy() {
  return (
    <section id="academia" className="py-20 bg-gray-950">
      <div className="max-w-7xl mx-auto px-4">
        <h2 className="text-4xl font-bold text-center text-white mb-12">
          UQ AI Academy
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {courses.map((c, i) => (
            <div
              key={i}
              className="bg-gradient-to-b from-gray-800 to-gray-900 rounded-xl p-6 border border-gray-700 hover:border-purple-500 transition-all"
            >
              <span className="text-purple-400 text-sm font-bold tracking-wider uppercase">
                {c.level}
              </span>
              <h3 className="text-2xl font-bold text-white mt-2 mb-4">
                {c.title}
              </h3>
              <p className="text-gray-400 mb-6">Duración: {c.duration}</p>
              <button className="text-white font-semibold hover:text-purple-400 transition-colors">
                Ver temario →
              </button>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
