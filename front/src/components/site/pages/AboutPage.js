export default function AboutPage({ onNavigate }) {
  return (
    <div className="space-y-10">
      {/* Header */}
      <section className="bg-gradient-to-r from-blue-600 to-indigo-600 text-white rounded-3xl p-12 text-center">
        <h2 className="text-4xl font-bold mb-4">À propos de ProductStore</h2>
        <p className="text-blue-100 max-w-2xl mx-auto text-lg">
          Fondée en 2020, ProductStore est devenue la référence en matière de produits technologiques de qualité.
        </p>
      </section>

      {/* Mission & Vision */}
      <section className="grid md:grid-cols-2 gap-8">
        <div className="bg-white border-2 border-blue-200 rounded-2xl p-8">
          <h3 className="text-2xl font-bold text-gray-900 mb-4">🎯 Notre Mission</h3>
          <p className="text-gray-600 leading-relaxed">
            Fournir les meilleurs produits technologiques avec un excellent service client. Nous croyons en la transparence et en la qualité au-dessus de tout.
          </p>
        </div>

        <div className="bg-white border-2 border-indigo-200 rounded-2xl p-8">
          <h3 className="text-2xl font-bold text-gray-900 mb-4">🚀 Notre Vision</h3>
          <p className="text-gray-600 leading-relaxed">
            Devenir le leader incontournable dans la distribution de produits technologiques en ligne, en offrant l'expérience client la plus exceptionnelle.
          </p>
        </div>
      </section>

      {/* Stats */}
      <section className="bg-gradient-to-r from-gray-50 to-blue-50 rounded-2xl p-8 border border-gray-200">
        <h3 className="text-2xl font-bold text-gray-900 mb-8 text-center">Nos Chiffres</h3>
        <div className="grid md:grid-cols-4 gap-6">
          {[
            { icon: "👥", number: "150K+", label: "Clients satisfaits" },
            { icon: "📦", number: "50K+", label: "Produits vendus" },
            { icon: "🌍", number: "180+", label: "Pays desservis" },
            { icon: "⭐", number: "4.9/5", label: "Note moyenne" },
          ].map((stat, i) => (
            <div key={i} className="text-center">
              <div className="text-4xl mb-2">{stat.icon}</div>
              <p className="text-3xl font-bold text-gray-900">{stat.number}</p>
              <p className="text-gray-600 text-sm mt-1">{stat.label}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Équipe */}
      <section>
        <h3 className="text-3xl font-bold text-gray-900 mb-8">Notre Équipe</h3>
        <div className="grid md:grid-cols-3 gap-6">
          {[
            { name: "Marie Dupont", role: "Fondatrice & CEO", emoji: "👩‍💼" },
            { name: "Jean Martin", role: "CTO", emoji: "👨‍💻" },
            { name: "Sophie Bernard", role: "Responsable Client", emoji: "👩‍💼" },
          ].map((member, i) => (
            <div key={i} className="bg-white border border-gray-200 rounded-2xl p-6 text-center hover:shadow-lg transition">
              <div className="text-6xl mb-4">{member.emoji}</div>
              <h4 className="font-bold text-lg text-gray-900">{member.name}</h4>
              <p className="text-blue-600 text-sm mt-1 font-semibold">{member.role}</p>
            </div>
          ))}
        </div>
      </section>

      {/* Valeurs */}
      <section>
        <h3 className="text-3xl font-bold text-gray-900 mb-8">Nos Valeurs</h3>
        <div className="grid md:grid-cols-3 gap-6">
          {[
            { icon: "💎", title: "Qualité", desc: "Tous nos produits sont contrôlés et certifiés." },
            { icon: "❤️", title: "Intégrité", desc: "Nous sommes transparents et honnêtes avec nos clients." },
            { icon: "🤝", title: "Solidarité", desc: "Nous soutienons les communautés locales." },
          ].map((value, i) => (
            <div key={i} className="bg-gradient-to-br from-blue-50 to-indigo-50 border border-blue-100 rounded-2xl p-6">
              <div className="text-4xl mb-4">{value.icon}</div>
              <h4 className="font-bold text-lg text-gray-900">{value.title}</h4>
              <p className="text-gray-600 text-sm mt-2">{value.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA */}
      <section className="bg-gradient-to-r from-blue-600 to-indigo-600 text-white rounded-2xl p-8 text-center">
        <h3 className="text-2xl font-bold mb-2">Rejoins notre communauté</h3>
        <p className="text-blue-100 mb-6">Sois le premier à découvrir nos nouveaux produits et offres exclusives.</p>
        <button className="bg-white text-blue-600 px-6 py-3 rounded-xl font-bold hover:bg-blue-50 transition">
          S'abonner à notre newsletter
        </button>
      </section>
    </div>
  );
}
