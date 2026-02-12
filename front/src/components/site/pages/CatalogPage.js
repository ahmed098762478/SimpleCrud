const PRODUCTS = [
  { id: 1, name: "Casque Audio", price: 59, desc: "Son clair, confortable.", icon: "🎧", rating: 4.8 },
  { id: 2, name: "Clavier Mécanique", price: 89, desc: "Switchs réactifs.", icon: "⌨️", rating: 4.9 },
  { id: 3, name: "Souris Gaming", price: 39, desc: "Précise et légère.", icon: "🖱️", rating: 4.7 },
  { id: 4, name: 'Écran 24"', price: 149, desc: "Full HD, belles couleurs.", icon: "🖥️", rating: 4.6 },
];

export default function CatalogPage({ onNavigate }) {
  return (
    <div className="space-y-10">
      {/* Header */}
      <div className="text-center">
        <h2 className="text-4xl font-bold text-gray-900">📦 Notre Catalogue</h2>
        <p className="text-gray-600 mt-4 max-w-2xl mx-auto text-lg">
          Découvrez notre sélection premium de produits technologiques. Chaque article a été choisi pour sa qualité et sa fiabilité.
        </p>
        <div className="flex gap-2 justify-center mt-6 flex-wrap">
          {["Tous", "Populaires", "En promo", "Nouveautés"].map((filter) => (
            <button
              key={filter}
              className="px-4 py-2 rounded-full border border-gray-300 hover:border-blue-600 hover:bg-blue-50 transition text-sm font-medium"
            >
              {filter}
            </button>
          ))}
        </div>
      </div>

      {/* Grid des produits */}
      <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-6">
        {PRODUCTS.map((p) => (
          <div
            key={p.id}
            className="group bg-white border border-gray-200 rounded-2xl overflow-hidden shadow-md hover:shadow-xl hover:-translate-y-2 transition-all duration-300"
          >
            {/* Image placeholder avec dégradé */}
            <div className="h-40 bg-gradient-to-br from-blue-100 to-indigo-100 grid place-items-center relative overflow-hidden">
              <div className="text-6xl group-hover:scale-110 transition-transform duration-300">
                {p.icon}
              </div>
              <div className="absolute top-3 right-3 bg-red-500 text-white text-xs font-bold px-3 py-1 rounded-full">
                -20%
              </div>
            </div>

            {/* Contenu */}
            <div className="p-5">
              <h3 className="font-bold text-gray-900 text-lg line-clamp-2">{p.name}</h3>
              <p className="text-gray-600 text-sm mt-2">{p.desc}</p>

              {/* Rating */}
              <div className="flex items-center gap-2 mt-3">
                <span className="text-yellow-400 text-sm">⭐ {p.rating}</span>
                <span className="text-gray-400 text-xs">(128 avis)</span>
              </div>

              {/* Prix et bouton */}
              <div className="mt-5 flex items-center justify-between">
                <div>
                  <span className="text-gray-500 line-through text-xs">{Math.ceil(p.price * 1.25)}€</span>
                  <p className="font-bold text-blue-600 text-xl">{p.price}€</p>
                </div>
                <button className="bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-700 hover:to-indigo-700 text-white p-3 rounded-xl shadow-lg hover:shadow-xl transition-all group-hover:scale-110 duration-300">
                  🛒
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* CTA */}
      <div className="bg-gradient-to-r from-gray-900 to-gray-800 text-white rounded-2xl p-8 text-center">
        <h3 className="text-2xl font-bold mb-2">Plus de 500+ produits disponibles</h3>
        <p className="text-gray-300 mb-4">Parcourez notre catalogue complet pour trouver exactement ce qu'il vous faut.</p>
        <button className="bg-white text-gray-900 px-6 py-3 rounded-xl font-bold hover:bg-gray-100 transition">
          Voir tous les produits →
        </button>
      </div>
    </div>
  );
}
