import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

// Site statique (une seule page)
import SitePage from "./components/site/pages/SitePage";

// Login
import LoginPage from "./components/Auth/LoginPage";

// Admin (tes composants existants)
import Layout from "./components/Layout/Layout";
import ProductTable from "./components/Product/ProductTable";
import AddProduct from "./components/Product/AddProduct";

function App() {
  return (
    <Router>
      <Routes>
        {/* SITE STATIQUE (une seule page avec tous les composants) */}
        <Route path="/" element={<SitePage />} />

        {/* LOGIN */}
        <Route path="/login" element={<LoginPage />} />

        {/* ADMIN */}
        <Route path="/admin" element={<Layout />}>
          <Route index element={<div className="text-center">Admin Home</div>} />
          <Route path="products" element={<ProductTable />} />
          <Route path="add-product" element={<AddProduct />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
