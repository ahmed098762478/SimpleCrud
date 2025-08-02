# Project1 - Application de Gestion de Produits

## 📋 Description

Ce projet est une application web full-stack pour la gestion de produits. Il comprend un backend Spring Boot avec une API REST et un frontend React avec une interface utilisateur moderne.

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework** : Spring Boot 3.5.3
- **Base de données** : H2 (base de données en mémoire)
- **ORM** : JPA/Hibernate
- **Java** : Version 17
- **API** : RESTful avec CORS activé

### Frontend (React)
- **Framework** : React 19.1.0
- **Styling** : Tailwind CSS
- **Build Tool** : Create React App
- **Tests** : Jest + Testing Library

## 📁 Structure du Projet

```
project1/
├── front/                    # Application React
│   ├── src/
│   │   ├── components/      # Composants React
│   │   │   ├── AddProduct.js
│   │   │   └── ProductTable.js
│   │   ├── App.js
│   │   └── index.js
│   ├── package.json
│   └── tailwind.config.js
├── project1/                 # Application Spring Boot
│   ├── src/main/java/com/example/project1/
│   │   ├── controller/      # Contrôleurs REST
│   │   │   └── ProductController.java
│   │   ├── entity/          # Entités JPA
│   │   │   ├── Product.java
│   │   │   ├── CategoryProduct.java
│   │   │   └── Supplier.java
│   │   ├── repository/      # Repositories
│   │   ├── service/         # Services métier
│   │   └── Project1Application.java
│   └── pom.xml
└── README.md
```

## 🚀 Installation et Démarrage

### Prérequis
- Java 17 ou supérieur
- Node.js 16 ou supérieur
- Maven 3.6 ou supérieur

### Backend (Spring Boot)

1. **Naviguer vers le dossier backend**
   ```bash
   cd project1
   ```

2. **Installer les dépendances Maven**
   ```bash
   mvn clean install
   ```

3. **Démarrer l'application Spring Boot**
   ```bash
   mvn spring-boot:run
   ```

   L'application sera accessible sur `http://localhost:8080`

### Frontend (React)

1. **Naviguer vers le dossier frontend**
   ```bash
   cd front
   ```

2. **Installer les dépendances Node.js**
   ```bash
   npm install
   ```

3. **Démarrer l'application React**
   ```bash
   npm start
   ```

   L'application sera accessible sur `http://localhost:3000`

## 🔧 API Endpoints

### Produits
- `GET /api/product` - Récupérer tous les produits
- `GET /api/product/{id}` - Récupérer un produit par ID
- `POST /api/product` - Créer un nouveau produit
- `PUT /api/product/{id}` - Mettre à jour un produit
- `DELETE /api/product/{id}` - Supprimer un produit

### Modèle de données Product
```json
{
  "id": 1,
  "name": "Nom du produit",
  "description": "Description du produit",
  "price": 99.99
}
```

## 🛠️ Technologies Utilisées

### Backend
- Spring Boot 3.5.3
- Spring Data JPA
- H2 Database
- Maven
- Java 17

### Frontend
- React 19.1.0
- Tailwind CSS 3.4.17
- Create React App
- Testing Library

## 📝 Fonctionnalités

- ✅ CRUD complet pour les produits
- ✅ API REST avec CORS configuré
- ✅ Interface utilisateur React
- ✅ Base de données H2 en mémoire
- ✅ Architecture MVC

## 🔮 Fonctionnalités à Développer

- [ ] Interface utilisateur complète pour la gestion des produits
- [ ] Validation des données côté client et serveur
- [ ] Authentification et autorisation
- [ ] Tests unitaires et d'intégration
- [ ] Documentation API avec Swagger
- [ ] Déploiement en production

## 🧪 Tests

### Backend
```bash
cd project1
mvn test
```

### Frontend
```bash
cd front
npm test
```

## 📦 Build

### Backend
```bash
cd project1
mvn clean package
```

### Frontend
```bash
cd front
npm run build
```

## 🤝 Contribution

1. Fork le projet
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 👥 Auteurs

- Développé avec ❤️ pour la gestion de produits

## 📞 Support

Pour toute question ou problème, veuillez ouvrir une issue sur le repository GitHub. 