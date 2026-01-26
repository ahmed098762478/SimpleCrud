import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const AddProduct = () => {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    // Ici tu peux envoyer vers API ou stocker en state global
    console.log("Produit ajouté :", { name, price });

    // après ajout => retour vers liste
    navigate("/products");
  };

  return (
    <div className="w-full max-w-xl bg-white p-6 rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-4 text-gray-800">
        Ajouter un produit
      </h2>

      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <input
          type="text"
          placeholder="Nom du produit"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="border p-2 rounded-lg"
          required
        />

        <input
          type="number"
          placeholder="Prix"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          className="border p-2 rounded-lg"
          required
        />

        <div className="flex gap-3">
          <button
            type="submit"
            className="bg-green-600 text-white px-4 py-2 rounded-lg shadow hover:bg-green-700"
          >
            Ajouter
          </button>

          <button
            type="button"
            onClick={() => navigate("/products")}
            className="bg-gray-500 text-white px-4 py-2 rounded-lg shadow hover:bg-gray-600"
          >
            Annuler
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddProduct;