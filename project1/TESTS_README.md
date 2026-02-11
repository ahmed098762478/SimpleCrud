# Tests Unitaires CRUD du Produit

Ce document décrit les tests unitaires et d'intégration pour le CRUD (Create, Read, Update, Delete) du module Product.

## 📁 Structure des Tests

```
src/test/java/com/example/project1/
├── entity/
│   └── ProductTests.java               # Tests de l'entité Product
├── repository/
│   └── ProductRepositoryTests.java     # Tests du repository
├── service/
│   └── ProductServiceTests.java        # Tests du service
├── controller/
│   └── ProductControllerTests.java     # Tests du contrôleur
└── ProductCRUDIntegrationTests.java    # Tests d'intégration complets

src/test/resources/
└── application-test.yml                # Configuration de test
```

## 📝 Description des Tests

### 1. ProductTests (Entité)
Tests unitaires pour la classe `Product` entity.

**Couverture:**
- ✅ Tests des getters et setters
- ✅ Validation des données
- ✅ Gestion des valeurs nulles
- ✅ Gestion des prix zéro et négatifs
- ✅ Gestion des caractères spéciaux et Unicode
- ✅ Précision des types de données
- ✅ Cohérence des appels multiples

**Nombre de tests:** 21

### 2. ProductRepositoryTests
Tests du repository utilisant la base de données H2 en mémoire.

**Couverture:**
- ✅ `save()` - Création et mise à jour
- ✅ `findAll()` - Récupération de tous les produits
- ✅ `findById()` - Recherche par ID
- ✅ `delete()` et `deleteById()` - Suppression
- ✅ `deleteAll()` - Suppression en masse
- ✅ `count()` - Comptage
- ✅ `existsById()` - Vérification d'existence
- ✅ Cycle CRUD complet
- ✅ Opérations concurrentes
- ✅ Persistance des données

**Nombre de tests:** 30

### 3. ProductServiceTests
Tests du service avec mocks du repository.

**Couverture:**
- ✅ `getAllProducts()` - Récupérer tous les produits
- ✅ `getProductById()` - Récupérer par ID
- ✅ `CreateProduct()` - Créer un nouveau produit
- ✅ `updateProduct()` - Mettre à jour un produit
- ✅ `deleteProduct()` - Supprimer un produit
- ✅ Gestion des cas de non-existence
- ✅ Mise à jour partielle
- ✅ Cycle CRUD complet
- ✅ Vérification des appels au repository

**Nombre de tests:** 18

### 4. ProductControllerTests
Tests du contrôleur REST avec mocks du service.

**Couverture:**
- ✅ GET /api/product - Récupérer tous
- ✅ GET /api/product/{id} - Récupérer par ID
- ✅ POST /api/product - Créer
- ✅ PUT /api/product/{id} - Mettre à jour
- ✅ DELETE /api/product/{id} - Supprimer
- ✅ Codes de statut HTTP
- ✅ Validation des réponses JSON
- ✅ Gestion des cas d'erreur
- ✅ Requêtes CORS
- ✅ Requêtes multiples en séquence

**Nombre de tests:** 18

### 5. ProductCRUDIntegrationTests
Tests d'intégration complets avec une vraie base de données H2.

**Couverture:**
- ✅ Cycle CRUD complet via API
- ✅ Création multiple de produits
- ✅ Validation des données
- ✅ Mise à jour sélective
- ✅ Suppression sélective
- ✅ Récupération de produits
- ✅ Gestion des listes
- ✅ Cohérence des données
- ✅ Opérations en masse
- ✅ Intégration complète (API → Service → Repository → DB)

**Nombre de tests:** 13

## 🚀 Exécution des Tests

### Exécuter tous les tests
```bash
mvn test
```

### Exécuter une classe de test spécifique
```bash
mvn test -Dtest=ProductServiceTests
```

### Exécuter un test spécifique
```bash
mvn test -Dtest=ProductServiceTests#testGetAllProducts
```

### Exécuter les tests avec couverture
```bash
mvn clean test jacoco:report
```

### Exécuter les tests en mode watch (avec Maven)
```bash
mvn test -Dtest=ProductServiceTests -DreuseForks=false
```

## 📊 Statistiques des Tests

| Classe de Test | Nombre de Tests | Type de Test |
|---|---|---|
| ProductTests | 21 | Unitaire |
| ProductRepositoryTests | 30 | Intégration (DB) |
| ProductServiceTests | 18 | Unitaire (Mock) |
| ProductControllerTests | 18 | Intégration (Mock) |
| ProductCRUDIntegrationTests | 13 | Intégration (E2E) |
| **Total** | **100** | **Mixte** |

## 🔍 Cas de Test Couverts

### Création (CREATE)
- ✅ Création avec tous les champs
- ✅ Création avec champs minimaux
- ✅ Création multiple
- ✅ Assignation automatique d'ID

### Lecture (READ)
- ✅ Récupérer tous les produits
- ✅ Récupérer par ID existant
- ✅ Récupérer par ID inexistant
- ✅ Listes vides

### Mise à Jour (UPDATE)
- ✅ Mise à jour complète
- ✅ Mise à jour partielle
- ✅ Mise à jour d'ID inexistant
- ✅ Cohérence des données après mise à jour

### Suppression (DELETE)
- ✅ Suppression par ID
- ✅ Suppression sélective
- ✅ Suppression en masse
- ✅ Suppression d'ID inexistant

### Validation & Erreurs
- ✅ Valeurs nulles
- ✅ Valeurs négatives (prix)
- ✅ Valeurs zéro
- ✅ Caractères spéciaux et Unicode

## 🛠️ Outils et Dépendances

- **JUnit 5** - Framework de test
- **Mockito** - Mocking framework
- **Spring Boot Test** - Testing support
- **Spring Test** - MockMvc
- **H2 Database** - Base de données en mémoire
- **AssertJ** - Assertions fluentes
- **Hamcrest** - Matchers

## 📋 Checklist de Test

- [x] Tests unitaires du service
- [x] Tests unitaires du contrôleur
- [x] Tests d'intégration du repository
- [x] Tests d'intégration API complète
- [x] Tests d'entité
- [x] Tests de validation des données
- [x] Tests de gestion d'erreurs
- [x] Tests de cohérence des données
- [x] Configuration de test (H2)
- [x] Documentation

## 💡 Bonnes Pratiques Utilisées

1. **Nomenclature claire** - Noms de tests descriptifs avec @DisplayName
2. **Arrangement-Act-Assert** - Structure AAA pour chaque test
3. **Tests isolés** - setUp() vide la BD avant chaque test
4. **Mocks appropriés** - Repository en service, Service en contrôleur
5. **Couverture complète** - Happy path et edge cases
6. **Documentation** - Tests servent de documentation executable

## ⚠️ Notes Importantes

1. Les tests utilisent H2 en mémoire (`application-test.yml`)
2. La base de données est créée et détruite à chaque exécution des tests
3. Les mocks sont réinitialisés avec `MockitoAnnotations.openMocks()`
4. Les tests sont exécutés en isolation avec `@BeforeEach`

## 🔗 References

- [JUnit 5 Documentation](https://junit.org/junit5/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing Guide](https://spring.io/guides/gs/testing-web/)
- [H2 Database](https://www.h2database.com/)

