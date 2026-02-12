import { Outlet } from "react-router-dom";
import Header from "./Header";
import Sidebar from "./Sidebar";

export default function Layout() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header />

      <div className="mx-auto max-w-7xl px-4 sm:px-6 py-6 flex gap-6">
        <Sidebar />

        <main className="flex-1">
          <div className="bg-white border border-gray-200 rounded-2xl shadow-sm p-5 sm:p-6 min-h-[70vh]">
            <Outlet />
          </div>

          <footer className="text-center text-xs text-gray-400 mt-6">
            © {new Date().getFullYear()} — Mon Application
          </footer>
        </main>
      </div>
    </div>
  );
}
