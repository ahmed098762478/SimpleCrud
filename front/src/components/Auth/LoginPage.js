
import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";

export default function LoginPage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({});

  const onSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};

    if (!email) newErrors.email = "L'email est requis";
    if (!password) newErrors.password = "Le mot de passe est requis";

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setErrors({});
    // Demo: pas de backend. On "simule" une connexion puis on revient au site.
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-indigo-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        {/* Card principale */}
        <div className="bg-white border border-gray-200 rounded-3xl p-8 shadow-lg hover:shadow-xl transition-shadow">
          {/* Header avec logo */}
          <div className="flex items-center gap-3 mb-6">
            <div className="h-12 w-12 rounded-2xl bg-gradient-to-br from-blue-600 to-indigo-600 grid place-items-center shadow-lg">
              <span className="text-white font-black text-lg">PS</span>
            </div>
            <div>
              <h1 className="text-xl font-bold text-gray-900">ProductStore</h1>
              <p className="text-xs text-gray-500">Espace Administration</p>
            </div>
          </div>

          <h2 className="text-2xl font-bold text-gray-900 mt-6">Bienvenue</h2>
          <p className="text-gray-600 mt-2 text-sm">
            Connecte-toi pour accéder à ton espace d'administration.
          </p>

          <form onSubmit={onSubmit} className="mt-8 space-y-5">
            {/* Champ Email */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                📧 Adresse email
              </label>
              <input
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  setErrors({ ...errors, email: "" });
                }}
                className={`w-full border-2 rounded-xl px-4 py-3 outline-none transition ${
                  errors.email
                    ? "border-red-300 bg-red-50 focus:ring-2 focus:ring-red-200"
                    : "border-gray-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                }`}
                placeholder="user@email.com"
                type="email"
              />
              {errors.email && (
                <p className="text-red-600 text-xs mt-1 font-medium">{errors.email}</p>
              )}
            </div>

            {/* Champ Mot de passe */}
            <div>
              <label className="block text-sm font-semibold text-gray-700 mb-2">
                🔒 Mot de passe
              </label>
              <input
                value={password}
                onChange={(e) => {
                  setPassword(e.target.value);
                  setErrors({ ...errors, password: "" });
                }}
                className={`w-full border-2 rounded-xl px-4 py-3 outline-none transition ${
                  errors.password
                    ? "border-red-300 bg-red-50 focus:ring-2 focus:ring-red-200"
                    : "border-gray-200 focus:border-blue-500 focus:ring-2 focus:ring-blue-100"
                }`}
                placeholder="••••••••"
                type="password"
              />
              {errors.password && (
                <p className="text-red-600 text-xs mt-1 font-medium">{errors.password}</p>
              )}
            </div>

            {/* Bouton de connexion */}
            <button
              type="submit"
              className="w-full bg-gradient-to-r from-blue-600 to-indigo-600 text-white py-3 rounded-xl shadow-lg hover:shadow-xl hover:scale-105 transition-all font-semibold flex items-center justify-center gap-2"
            >
              <span>🚀</span>
              <span>Se connecter</span>
            </button>
          </form>

          {/* Divider */}
          <div className="flex items-center gap-3 my-6">
            <div className="flex-1 h-px bg-gray-200"></div>
            <span className="text-xs text-gray-500 px-2">ou</span>
            <div className="flex-1 h-px bg-gray-200"></div>
          </div>

          {/* Retour au site */}
          <Link
            to="/"
            className="block w-full border-2 border-gray-200 text-gray-700 py-3 rounded-xl text-center hover:bg-gray-50 transition font-semibold"
          >
            ← Retour au site
          </Link>

          {/* Info de démo */}
          <div className="mt-6 p-4 bg-blue-50 border border-blue-100 rounded-2xl">
            <p className="text-xs text-blue-700 font-medium">
              💡 Démo : Utilisez n'importe quel email et mot de passe
            </p>
          </div>
        </div>

        {/* Footer */}
        <p className="text-center text-xs text-gray-500 mt-6">
          © 2024 ProductStore. Tous droits réservés.
        </p>
      </div>
    </div>
  );
}
