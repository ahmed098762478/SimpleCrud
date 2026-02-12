export default function ContactPage({ onNavigate }) {
  return (
    <div className="space-y-10">
      {/* Header */}
      <div className="text-center">
        <h2 className="text-4xl font-bold text-gray-900">📞 Nous Contacter</h2>
        <p className="text-gray-600 mt-4 max-w-2xl mx-auto text-lg">
          Vous avez une question ? Nous sommes là pour vous aider. Contactez-nous par le formulaire ou par l'un de nos canaux.
        </p>
      </div>

      {/* Contact Grid */}
      <div className="grid md:grid-cols-3 gap-6 mb-8">
        {[
          { icon: "📧", title: "Email", value: "contact@productstore.com", color: "from-blue-600 to-indigo-600" },
          { icon: "📱", title: "Téléphone", value: "+212 600 000 000", color: "from-purple-600 to-pink-600" },
          { icon: "📍", title: "Adresse", value: "Casablanca, Maroc", color: "from-green-600 to-emerald-600" },
        ].map((contact, i) => (
          <div key={i} className={`bg-gradient-to-br ${contact.color} text-white rounded-2xl p-6 shadow-lg`}>
            <div className="text-4xl mb-3">{contact.icon}</div>
            <h3 className="font-bold text-lg">{contact.title}</h3>
            <p className="text-white text-opacity-90 mt-2">{contact.value}</p>
          </div>
        ))}
      </div>

      {/* Form & Map Grid */}
      <div className="grid md:grid-cols-2 gap-8">
        {/* Formulaire de contact */}
        <div className="bg-white border border-gray-200 rounded-2xl p-8 shadow-lg">
          <h3 className="font-bold text-gray-900 text-xl mb-6">Envoyer un message</h3>

          <form className="space-y-5">
            {/* Nom */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Votre nom</label>
              <input
                type="text"
                placeholder="Ex: Jean Dupont"
                className="w-full border-2 border-gray-200 rounded-xl px-4 py-3 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition"
              />
            </div>

            {/* Email */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Votre email</label>
              <input
                type="email"
                placeholder="exemple@email.com"
                className="w-full border-2 border-gray-200 rounded-xl px-4 py-3 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition"
              />
            </div>

            {/* Sujet */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Sujet</label>
              <select className="w-full border-2 border-gray-200 rounded-xl px-4 py-3 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition">
                <option>Sélectionner un sujet...</option>
                <option>🛍️ Commande</option>
                <option>📦 Livraison</option>
                <option>💬 Retour produit</option>
                <option>❓ Question générale</option>
              </select>
            </div>

            {/* Message */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">Message</label>
              <textarea
                placeholder="Écris ton message ici..."
                rows={5}
                className="w-full border-2 border-gray-200 rounded-xl px-4 py-3 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition resize-none"
              ></textarea>
            </div>

            {/* Checkbox */}
            <label className="flex items-center gap-3 cursor-pointer">
              <input type="checkbox" className="w-5 h-5 rounded border-gray-300 text-blue-600" />
              <span className="text-sm text-gray-600">J'accepte les conditions d'utilisation</span>
            </label>

            {/* Bouton */}
            <button
              type="button"
              className="w-full bg-gradient-to-r from-blue-600 to-indigo-600 text-white py-3 rounded-xl font-bold shadow-lg hover:shadow-xl hover:scale-105 transition-all flex items-center justify-center gap-2"
            >
              <span>📤</span>
              <span>Envoyer le message</span>
            </button>
          </form>
        </div>

        {/* Info & Map */}
        <div className="space-y-6">
          {/* Info */}
          <div className="bg-gradient-to-br from-blue-50 to-indigo-50 border-2 border-blue-100 rounded-2xl p-8">
            <h3 className="font-bold text-gray-900 text-xl mb-6">Informations de contact</h3>

            <div className="space-y-4">
              <div>
                <p className="text-sm font-semibold text-gray-700 mb-1">📧 Email</p>
                <p className="text-blue-600 font-medium">contact@productstore.com</p>
              </div>

              <div>
                <p className="text-sm font-semibold text-gray-700 mb-1">📱 Téléphone</p>
                <p className="text-blue-600 font-medium">+212 600 000 000</p>
              </div>

              <div>
                <p className="text-sm font-semibold text-gray-700 mb-1">🕒 Heures d'ouverture</p>
                <p className="text-gray-600">Lun - Ven : 9h - 18h</p>
                <p className="text-gray-600">Sam - Dim : Fermé</p>
              </div>

              <div className="pt-4 border-t border-blue-200">
                <p className="text-sm font-semibold text-gray-700 mb-3">📍 Localisation</p>
                <p className="text-gray-600">
                  ProductStore<br />
                  123, Boulevard Hassan II<br />
                  20000 Casablanca<br />
                  Maroc
                </p>
              </div>
            </div>
          </div>

          {/* Map placeholder */}
          <div className="h-64 rounded-2xl bg-gradient-to-br from-gray-200 to-gray-300 grid place-items-center border-2 border-gray-300 overflow-hidden hover:border-blue-400 transition">
            <div className="text-center">
              <div className="text-5xl mb-2">🗺️</div>
              <p className="text-gray-600 font-medium">Carte interactive</p>
              <p className="text-gray-500 text-sm">(Intégration Google Maps)</p>
            </div>
          </div>
        </div>
      </div>

      {/* Réseaux sociaux */}
      <section className="bg-white border border-gray-200 rounded-2xl p-8 text-center">
        <h3 className="text-2xl font-bold text-gray-900 mb-6">Nous suivre sur les réseaux</h3>
        <div className="flex gap-4 justify-center flex-wrap">
          {[
            { icon: "f", name: "Facebook", color: "bg-blue-600" },
            { icon: "𝕏", name: "Twitter", color: "bg-black" },
            { icon: "📷", name: "Instagram", color: "bg-pink-600" },
            { icon: "in", name: "LinkedIn", color: "bg-blue-700" },
          ].map((social, i) => (
            <a
              key={i}
              href="#"
              className={`${social.color} text-white w-12 h-12 rounded-full grid place-items-center font-bold text-lg hover:scale-110 transition-transform shadow-lg`}
              title={social.name}
            >
              {social.icon}
            </a>
          ))}
        </div>
      </section>

      {/* FAQ */}
      <section>
        <h3 className="text-2xl font-bold text-gray-900 mb-6">Questions fréquemment posées</h3>
        <div className="space-y-4">
          {[
            { q: "Quel est le délai de livraison ?", a: "Les commandes sont livrées en 2-5 jours ouvrables." },
            { q: "Proposez-vous un retour gratuit ?", a: "Oui, retour gratuit sous 30 jours après achat." },
            { q: "Comment suivre ma commande ?", a: "Un numéro de suivi est envoyé par email après l'expédition." },
          ].map((faq, i) => (
            <details key={i} className="bg-white border border-gray-200 rounded-xl p-4 cursor-pointer hover:border-blue-300 transition">
              <summary className="font-semibold text-gray-900 flex items-center gap-2">
                <span>❓</span>
                {faq.q}
              </summary>
              <p className="text-gray-600 mt-3 ml-6">{faq.a}</p>
            </details>
          ))}
        </div>
      </section>
    </div>
  );
}
