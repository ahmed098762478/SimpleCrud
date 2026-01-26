import React from "react";
import { useNavigate } from "react-router-dom";

const ProductTable = () => {
  const navigate = useNavigate();

  // Exemple de produits (plus tard tu peux les récupérer depuis une API)
  const products = [
    { id: 1, name: "PC Portable", price: 700 },
    { id: 2, name: "Clavier", price: 25 },
    { id: 3, name: "Souris", price: 15 },
  ];

  return (
    <div className="w-full max-w-3xl bg-white p-6 rounded-lg shadow-lg">
      <div className="flex items-center justify-between mb-4">
        <h2 className="text-2xl font-bold text-gray-800">Liste des produits</h2>

        <button
          onClick={() => navigate("/add-product")}
          className="bg-blue-600 text-white px-4 py-2 rounded-lg shadow hover:bg-blue-700"
        >
          Ajouter un produit
        </button>
      </div>

      <table className="w-full border-collapse border border-gray-300">
        <thead>
          <tr className="bg-gray-200 text-gray-700">
            <th className="border border-gray-300 p-2">ID</th>
            <th className="border border-gray-300 p-2">Nom</th>
            <th className="border border-gray-300 p-2">Prix</th>
          </tr>
        </thead>

        <tbody>
          {products.map((p) => (
            <tr key={p.id} className="text-center">
              <td className="border border-gray-300 p-2">{p.id}</td>
              <td className="border border-gray-300 p-2">{p.name}</td>
              <td className="border border-gray-300 p-2">{p.price} DH</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ProductTable;