export default function Lab() {
  return (
    <section id="lab" className="py-20 bg-gray-900">
      <div className="max-w-7xl mx-auto px-4 flex flex-col md:flex-row items-center gap-12">
        <div className="md:w-1/2">
          <h2 className="text-4xl font-bold text-white mb-6">UQ AI Lab</h2>
          <p className="text-lg text-gray-400 mb-6">
            Nuestro espacio de innovación donde nacen los prototipos del futuro.
            Colaboramos con estudiantes universitarios e investigadores para
            desarrollar soluciones de Inteligencia Artificial aplicada.
          </p>
          <ul className="space-y-3 text-gray-300">
            <li>✨ Prototipos y Demos experimentales</li>
            <li>🔬 Investigación aplicada en IA</li>
            <li>🤝 Comunidad activa de desarrolladores</li>
          </ul>
        </div>
        <div className="md:w-1/2 bg-gray-800 rounded-2xl p-8 border border-gray-700">
          <div className="aspect-video bg-gray-900 rounded-lg flex items-center justify-center border border-dashed border-gray-600">
            <span className="text-gray-500">Espacio para Demo de IA</span>
          </div>
        </div>
      </div>
    </section>
  );
}
