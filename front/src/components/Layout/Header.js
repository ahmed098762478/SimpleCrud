import { Link } from "react-router-dom";

export default function Header() {
  return (
    <header className="sticky top-0 z-30 w-full bg-white/80 backdrop-blur border-b border-gray-200">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 py-4 flex items-center justify-between">
        {/* Brand */}
        <div className="flex items-center gap-3">
          <div className="h-10 w-10 rounded-2xl bg-blue-600 grid place-items-center shadow">
            <span className="font-black text-white">A</span>
          </div>
          <div className="leading-tight">
            <h1 className="text-lg sm:text-xl font-bold text-gray-900">
              Mon Application
            </h1>
            <p className="text-xs sm:text-sm text-gray-500">
              Gestion des produits
            </p>
          </div>
        </div>

        {/* Actions */}
        <div className="flex items-center gap-3">
          <Link
            to="/add-product"
            className="hidden sm:inline-flex items-center gap-2 bg-blue-600 text-white px-4 py-2 rounded-xl shadow hover:bg-blue-700 transition"
          >
            + Ajouter
          </Link>

          {/* Avatar */}
          <div className="h-10 w-10 rounded-full bg-gray-200 grid place-items-center">
            <span className="text-sm font-semibold text-gray-700">UI</span>
          </div>
        </div>
      </div>
    </header>
  );
}
