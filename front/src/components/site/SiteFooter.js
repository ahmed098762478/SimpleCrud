export default function SiteFooter() {
  return (
    <footer className="bg-gradient-to-b from-gray-900 to-black text-white mt-20">
      <div className="max-w-6xl mx-auto px-4 py-16">
        {/* Footer Grid */}
        <div className="grid md:grid-cols-4 gap-10 mb-12">
          {/* Brand */}
          <div>
            <div className="flex items-center gap-2 mb-4">
              <div className="h-10 w-10 rounded-lg bg-gradient-to-br from-blue-500 to-indigo-600 grid place-items-center">
                <span className="text-white font-black">PS</span>
              </div>
              <span className="font-bold text-lg">ProductStore</span>
            </div>
            <p className="text-gray-400 text-sm">
              La référence des produits technologiques de qualité. Livraison rapide, support 7j/7.
            </p>
          </div>

          {/* Quick Links */}
          <div>
            <h4 className="font-bold text-white mb-4">Liens rapides</h4>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li><a href="#" className="hover:text-blue-400 transition">Accueil</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">Produits</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">À propos</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">Contact</a></li>
            </ul>
          </div>

          {/* Support */}
          <div>
            <h4 className="font-bold text-white mb-4">Support</h4>
            <ul className="space-y-2 text-gray-400 text-sm">
              <li><a href="#" className="hover:text-blue-400 transition">FAQ</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">Politique de retour</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">Conditions d'utilisation</a></li>
              <li><a href="#" className="hover:text-blue-400 transition">Confidentialité</a></li>
            </ul>
          </div>

          {/* Newsletter */}
          <div>
            <h4 className="font-bold text-white mb-4">Newsletter</h4>
            <p className="text-gray-400 text-sm mb-4">
              Reçois les dernières offres et nouveautés
            </p>
            <div className="flex gap-2">
              <input
                type="email"
                placeholder="Ton email..."
                className="flex-1 px-3 py-2 rounded-lg bg-gray-800 border border-gray-700 text-white placeholder-gray-500 outline-none focus:border-blue-400 transition"
              />
              <button className="bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-lg font-semibold transition">
                ✓
              </button>
            </div>
          </div>
        </div>

        {/* Divider */}
        <div className="border-t border-gray-700 my-8"></div>

        {/* Bottom Footer */}
        <div className="flex flex-col md:flex-row items-center justify-between gap-4">
          <p className="text-gray-400 text-sm">
            © {new Date().getFullYear()} ProductStore. Tous droits réservés.
          </p>

          {/* Social Links */}
          <div className="flex gap-4">
            {[
              { icon: "f", label: "Facebook" },
              { icon: "𝕏", label: "Twitter" },
              { icon: "📷", label: "Instagram" },
              { icon: "in", label: "LinkedIn" },
            ].map((social, i) => (
              <a
                key={i}
                href="#"
                title={social.label}
                className="w-10 h-10 rounded-full bg-gray-800 hover:bg-blue-600 grid place-items-center transition transform hover:scale-110"
              >
                {social.icon}
              </a>
            ))}
          </div>

          <p className="text-gray-400 text-xs">
            Conçu avec ❤️ par ProductStore
          </p>
        </div>
      </div>
    </footer>
  );
}
