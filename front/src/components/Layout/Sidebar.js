import { NavLink } from "react-router-dom";

export default function Sidebar() {
  const navLinkClass = ({ isActive }) =>
    `flex items-center gap-3 px-4 py-3 rounded-xl transition
     ${
       isActive
         ? "bg-blue-600 text-white shadow"
         : "text-gray-700 hover:bg-blue-50 hover:text-blue-700"
     }`;

  return (
    <aside className="w-72 hidden md:block">
      <div className="bg-white border border-gray-200 rounded-2xl shadow-sm p-4 sticky top-24">
        <p className="text-xs font-semibold text-gray-500 uppercase tracking-wider px-2">
          Navigation
        </p>

        <nav className="mt-3 space-y-2">
          <NavLink to="/" end className={navLinkClass}>
            <span className="h-9 w-9 rounded-xl bg-gray-100 grid place-items-center">
              🏠
            </span>
            <div className="flex flex-col">
              <span className="font-semibold">Accueil</span>
              <span className="text-xs opacity-80">Vue générale</span>
            </div>
          </NavLink>

          <NavLink to="/products" className={navLinkClass}>
            <span className="h-9 w-9 rounded-xl bg-gray-100 grid place-items-center">
              📦
            </span>
            <div className="flex flex-col">
              <span className="font-semibold">Produits</span>
              <span className="text-xs opacity-80">Liste & gestion</span>
            </div>
          </NavLink>

          <NavLink to="/add-product" className={navLinkClass}>
            <span className="h-9 w-9 rounded-xl bg-gray-100 grid place-items-center">
              ➕
            </span>
            <div className="flex flex-col">
              <span className="font-semibold">Ajouter</span>
              <span className="text-xs opacity-80">Nouveau produit</span>
            </div>
          </NavLink>
        </nav>

        {/* Bloc info */}
        <div className="mt-6 rounded-2xl bg-gradient-to-br from-blue-50 to-white border border-blue-100 p-4">
          <p className="text-sm font-semibold text-gray-900">Astuce ✨</p>
          <p className="text-sm text-gray-600 mt-1">
            Clique sur “+ Ajouter” pour créer un produit rapidement.
          </p>
        </div>
      </div>
    </aside>
  );
}
