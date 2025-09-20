import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <header className="bg-blue-600 text-white w-full p-6 text-center shadow-lg">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 className="">Bienvenue dans mon application</h1>
      </header>
    </div>
  );
}

export default App;
