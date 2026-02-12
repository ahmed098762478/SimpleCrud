import { Link } from "react-router-dom";

export default function SiteHeader({ onNavigate }) {
  const navBtnClass = (isActive) =>
    `px-4 py-2 rounded-lg transition cursor-pointer font-semibold ${
      isActive ? "text-blue-700 bg-blue-50 border-b-2 border-blue-600" : "text-gray-700 hover:text-blue-600 hover:bg-gray-50"
    }`;

  return (
    <header className="w-full bg-gradient-to-r from-white to-blue-50 border-b border-gray-200 shadow-sm sticky top-0 z-50">
      <div className="max-w-6xl mx-auto px-4 py-5 flex items-center justify-between">
        {/* Logo / Brand */}
        <button
          onClick={() => onNavigate("home")}
          className="flex items-center gap-3 cursor-pointer hover:opacity-80 transition group"
        >
          <div className="h-11 w-11 rounded-2xl bg-gradient-to-br from-blue-600 to-indigo-600 grid place-items-center shadow-lg group-hover:shadow-xl transition">
            <span className="text-white font-black text-lg">PS</span>
          </div>
          <div className="leading-tight hidden sm:block">
            <p className="font-bold text-gray-900 text-base">ProductStore</p>
            <p className="text-xs text-gray-500">Vos produits préférés</p>
          </div>
        </button>

        {/* Nav */}
        <nav className="hidden md:flex items-center gap-1">
          <button onClick={() => onNavigate("home")} className={navBtnClass(false)}>
            🏠 Accueil
          </button>
          <button onClick={() => onNavigate("catalog")} className={navBtnClass(false)}>
            📦 Produits
          </button>
          <button onClick={() => onNavigate("about")} className={navBtnClass(false)}>
            ℹ️ À propos
          </button>
          <button onClick={() => onNavigate("contact")} className={navBtnClass(false)}>
            📞 Contact
          </button>
        </nav>

        {/* Action buttons */}
        <div className="flex items-center gap-3">
          {/* Panier (icon) */}
          <button className="relative p-2.5 rounded-xl hover:bg-gray-100 transition group hidden sm:flex">
            <span className="text-xl">🛒</span>
            <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs font-bold w-5 h-5 rounded-full grid place-items-center group-hover:scale-110 transition">
              3
            </span>
          </button>

          {/* Login button */}
          <Link
            to="/login"
            className="bg-gradient-to-r from-blue-600 to-indigo-600 text-white px-5 py-2.5 rounded-xl shadow-lg hover:shadow-xl hover:scale-105 transition-all font-semibold text-sm flex items-center gap-2"
          >
            <span>🔐</span>
            <span className="hidden sm:inline">Login</span>
          </Link>
        </div>
      </div>
    </header>
  );
}
