export default function HomePage({ onNavigate }) {
  return (
    <div className="space-y-12">
      {/* Hero Section */}
      <section className="bg-gradient-to-r from-blue-600 to-indigo-600 rounded-3xl p-8 md:p-12 shadow-lg text-white overflow-hidden relative">
        <div className="absolute top-0 right-0 w-40 h-40 bg-white opacity-10 rounded-full -mr-20 -mt-20"></div>
        <div className="absolute bottom-0 left-0 w-32 h-32 bg-white opacity-10 rounded-full -ml-16 -mb-16"></div>

        <div className="relative z-10 flex flex-col md:flex-row items-start md:items-center justify-between gap-8">
          <div>
            <h2 className="text-4xl md:text-5xl font-extrabold leading-tight">
              🎉 Découvrez nos produits exclusifs
            </h2>
            <p className="text-blue-100 mt-4 max-w-2xl text-lg">
              Une sélection soigneuse de produits de qualité, livrés rapidement et avec un excellent support client.
            </p>

            <div className="mt-8 flex gap-4 flex-wrap">
              <button
                onClick={() => onNavigate("catalog")}
                className="bg-white text-blue-600 px-6 py-3 rounded-xl shadow-lg hover:shadow-xl hover:scale-105 transition-all font-bold flex items-center gap-2"
              >
                <span>📦</span>
                <span>Voir le catalogue</span>
              </button>
              <button
                onClick={() => onNavigate("about")}
                className="border-2 border-white text-white px-6 py-3 rounded-xl hover:bg-white hover:text-blue-600 transition-all font-bold"
              >
                ℹ️ En savoir plus
              </button>
            </div>
          </div>

          {/* Promo Badge */}
          <div className="bg-white bg-opacity-20 backdrop-blur-md border border-white border-opacity-30 rounded-2xl p-6 min-w-fit">
            <p className="text-sm font-bold text-blue-100">🎁 OFFRE SPÉCIALE</p>
            <p className="text-3xl font-black text-white mt-2">-20%</p>
            <p className="text-blue-100 mt-1 text-sm">
              Sur une sélection de produits premium
            </p>
          </div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="grid md:grid-cols-3 gap-6">
        {[
          { icon: "⭐", title: "4.9/5", desc: "Note client", value: "2500+ avis" },
          { icon: "🚚", title: "24h", desc: "Expédition rapide", value: "Toute la France" },
          { icon: "💬", title: "7j/7", desc: "Support client", value: "Réponse en 2h" },
        ].map((stat, i) => (
          <div
            key={i}
            className="bg-white border-2 border-gray-200 rounded-2xl p-6 shadow-md hover:shadow-lg hover:-translate-y-1 transition-all"
          >
            <div className="text-4xl mb-3">{stat.icon}</div>
            <h3 className="text-2xl font-bold text-gray-900">{stat.title}</h3>
            <p className="text-gray-600 text-sm mt-1">{stat.desc}</p>
            <p className="text-blue-600 font-semibold text-xs mt-2">{stat.value}</p>
          </div>
        ))}
      </section>

      {/* Features Section */}
      <section>
        <h3 className="text-3xl font-bold text-gray-900 mb-8">Pourquoi nous choisir ?</h3>
        <div className="grid sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {[
            { icon: "✅", title: "Qualité", desc: "Produits sélectionnés et testés rigoureusement." },
            { icon: "📦", title: "Livraison sécurisée", desc: "Expédition rapide et suivi en temps réel." },
            { icon: "🛡️", title: "Garantie 100%", desc: "Satisfait ou remboursé sous 30 jours." },
            { icon: "💰", title: "Meilleur prix", desc: "Prix garantis compétitifs du marché." },
            { icon: "🔄", title: "Retours faciles", desc: "Retour gratuit sans questions posées." },
            { icon: "🌍", title: "Livraison mondiale", desc: "Expéditions vers 180+ pays." },
          ].map((feature, i) => (
            <div
              key={i}
              className="bg-gradient-to-br from-gray-50 to-white border border-gray-200 rounded-2xl p-6 hover:shadow-lg hover:border-blue-300 transition-all"
            >
              <div className="text-3xl mb-4">{feature.icon}</div>
              <h4 className="font-bold text-gray-900 text-lg">{feature.title}</h4>
              <p className="text-gray-600 text-sm mt-2">{feature.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Call to Action */}
      <section className="bg-gradient-to-r from-indigo-50 to-blue-50 border-2 border-indigo-200 rounded-2xl p-8 text-center">
        <h3 className="text-2xl font-bold text-gray-900 mb-2">Prêt à commencer ?</h3>
        <p className="text-gray-600 mb-6">Explorez notre large gamme de produits et trouvez ce qui vous convient.</p>
        <button
          onClick={() => onNavigate("catalog")}
          className="bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-xl shadow-lg hover:shadow-xl font-bold transition-all inline-flex items-center gap-2"
        >
          <span>🛍️</span>
          <span>Parcourir le catalogue</span>
        </button>
      </section>
    </div>
  );
}
