
import { Link, Outlet } from "react-router-dom";

export default function Layout() {
  return (
    <div className="flex flex-col min-h-screen bg-gray-100">
      {/* Header */}
      <header className="bg-blue-600 text-white w-full p-6 text-center shadow-lg flex flex-col items-center">
        
        <h1 className="text-2xl font-bold">Bienvenue dans mon application</h1>

        {/* Navigation */}
        <nav className="mt-4 flex gap-4">
          <Link
            to="/"
            className="bg-white text-blue-600 px-4 py-2 rounded-lg shadow hover:bg-gray-200"
          >
            Accueil
          </Link>

          <Link
            to="/products"
            className="bg-white text-blue-600 px-4 py-2 rounded-lg shadow hover:bg-gray-200"
          >
            Liste Produits
          </Link>
        </nav>
      </header>

      {/* Ici s'affiche UNE seule page à la fois */}
      <main className="flex-grow flex items-center justify-center p-6">
        <Outlet />
      </main>
    </div>
  );
}