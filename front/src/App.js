import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Layout from "./components/Layout/Layout";
import ProductTable from "./components/Product/ProductTable";
import AddProduct from "./components/Product/AddProduct";

function Home() {
  return (
    <div className="text-center">
      <h2 className="text-xl font-semibold">Page d'accueil 👋</h2>
      <p className="text-gray-600 mt-2">Choisis une section dans le menu.</p>
    </div>
  );
}

function App() {
  return (
    <Router>
      <Routes>
        {/* Layout général */}
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />

          {/* Chaque composant dans son interface */}
          <Route path="products" element={<ProductTable />} />
          <Route path="add-product" element={<AddProduct />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;