export default function Hero() {
  return (
    <section className="relative bg-gray-950 text-white min-h-[80vh] flex items-center justify-center overflow-hidden">
      {/* Fondo con gradiente sutil para darle el toque de IA */}
      <div className="absolute inset-0 bg-gradient-to-br from-blue-900/30 to-purple-900/30 z-0"></div>

      <div className="relative z-10 max-w-4xl mx-auto px-4 text-center">
        <h1 className="text-5xl md:text-7xl font-extrabold mb-6 tracking-tight bg-clip-text text-transparent bg-gradient-to-r from-blue-400 to-purple-500">
          UQ AI Solution
        </h1>
        <p className="text-xl md:text-2xl text-gray-300 mb-10">
          Inteligencia Artificial para el Perú y el Mundo
        </p>
        <div className="flex flex-col sm:flex-row justify-center gap-4">
          <a
            href="#servicios"
            className="px-8 py-4 bg-blue-600 hover:bg-blue-500 rounded-lg font-bold transition-colors"
          >
            Explorar Servicios
          </a>
          <a
            href="#contacto"
            className="px-8 py-4 bg-transparent border border-gray-500 hover:border-white rounded-lg font-bold transition-colors"
          >
            Contactar
          </a>
        </div>
      </div>
    </section>
  );
}
