import SiteHeader from "../SiteHeader";
import SiteFooter from "../SiteFooter";
import HomePage from "./HomePage";
import CatalogPage from "./CatalogPage";
import AboutPage from "./AboutPage";
import ContactPage from "./ContactPage";

export default function SitePage() {
  const handleScroll = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: "smooth" });
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      <SiteHeader
        onNavigate={handleScroll}
        currentSection={null}
      />

      <main className="flex-1">
        {/* Section Accueil */}
        <section id="home" className="bg-white border-b border-gray-200">
          <div className="max-w-6xl mx-auto px-4 py-8">
            <HomePage onNavigate={handleScroll} />
          </div>
        </section>

        {/* Section Produits */}
        <section id="catalog" className="bg-gray-50 border-b border-gray-200">
          <div className="max-w-6xl mx-auto px-4 py-8">
            <CatalogPage onNavigate={handleScroll} />
          </div>
        </section>

        {/* Section À propos */}
        <section id="about" className="bg-white border-b border-gray-200">
          <div className="max-w-6xl mx-auto px-4 py-8">
            <AboutPage onNavigate={handleScroll} />
          </div>
        </section>

        {/* Section Contact */}
        <section id="contact" className="bg-gray-50">
          <div className="max-w-6xl mx-auto px-4 py-8">
            <ContactPage onNavigate={handleScroll} />
          </div>
        </section>
      </main>

      <SiteFooter />
    </div>
  );
}
