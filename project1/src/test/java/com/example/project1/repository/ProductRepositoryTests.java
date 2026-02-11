package com.example.project1.repository;

import com.example.project1.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Product Repository Tests")
class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private Product product2;

    @BeforeEach
    void setUp() {
        // Nettoyer la base de données avant chaque test
        productRepository.deleteAll();

        // Initialisation des produits de test
        product = new Product();
        product.setName("Laptop");
        product.setDescription("High-performance laptop");
        product.setPrice(999.99);

        product2 = new Product();
        product2.setName("Mouse");
        product2.setDescription("Wireless mouse");
        product2.setPrice(29.99);
    }

    // ==================== Tests pour save ====================

    @Test
    @DisplayName("Should save a product successfully")
    void testSaveProduct() {
        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        assertNotNull(savedProduct.getId());
        assertEquals("Laptop", savedProduct.getName());
        assertEquals("High-performance laptop", savedProduct.getDescription());
        assertEquals(999.99, savedProduct.getPrice());
    }

    @Test
    @DisplayName("Should save multiple products")
    void testSaveMultipleProducts() {
        // Act
        Product saved1 = productRepository.save(product);
        Product saved2 = productRepository.save(product2);

        // Assert
        assertNotNull(saved1.getId());
        assertNotNull(saved2.getId());
        assertNotEquals(saved1.getId(), saved2.getId());
    }

    @Test
    @DisplayName("Should update an existing product")
    void testUpdateProduct() {
        // Arrange
        Product savedProduct = productRepository.save(product);
        int productId = savedProduct.getId();

        // Act
        savedProduct.setName("Updated Laptop");
        savedProduct.setPrice(1199.99);
        Product updatedProduct = productRepository.save(savedProduct);

        // Assert
        assertEquals(productId, updatedProduct.getId());
        assertEquals("Updated Laptop", updatedProduct.getName());
        assertEquals(1199.99, updatedProduct.getPrice());
    }

    // ==================== Tests pour findAll ====================

    @Test
    @DisplayName("Should find all products")
    void testFindAllProducts() {
        // Arrange
        productRepository.save(product);
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    @DisplayName("Should return empty list when no products exist")
    void testFindAllProductsEmpty() {
        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    @DisplayName("Should return correct product details in findAll")
    void testFindAllProductsDetails() {
        // Arrange
        Product saved1 = productRepository.save(product);
        Product saved2 = productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertEquals(2, products.size());
        assertTrue(products.stream()
                .anyMatch(p -> p.getId() == saved1.getId() && "Laptop".equals(p.getName())));
        assertTrue(products.stream()
                .anyMatch(p -> p.getId() == saved2.getId() && "Mouse".equals(p.getName())));
    }

    // ==================== Tests pour findById ====================

    @Test
    @DisplayName("Should find product by id")
    void testFindProductById() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct.getId(), foundProduct.get().getId());
        assertEquals("Laptop", foundProduct.get().getName());
    }

    @Test
    @DisplayName("Should return empty Optional when product not found")
    void testFindProductByIdNotFound() {
        // Act
        Optional<Product> foundProduct = productRepository.findById(999);

        // Assert
        assertFalse(foundProduct.isPresent());
    }

    @Test
    @DisplayName("Should find correct product among multiple")
    void testFindProductByIdAmongMultiple() {
        // Arrange
        Product saved1 = productRepository.save(product);
        Product saved2 = productRepository.save(product2);

        // Act
        Optional<Product> foundProduct = productRepository.findById(saved2.getId());

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals("Mouse", foundProduct.get().getName());
        assertEquals(29.99, foundProduct.get().getPrice());
    }

    @Test
    @DisplayName("Should retrieve all product fields correctly")
    void testFindProductByIdAllFields() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // Assert
        assertTrue(foundProduct.isPresent());
        Product p = foundProduct.get();
        assertEquals("Laptop", p.getName());
        assertEquals("High-performance laptop", p.getDescription());
        assertEquals(999.99, p.getPrice());
    }

    // ==================== Tests pour deleteById ====================

    @Test
    @DisplayName("Should delete product by id")
    void testDeleteProductById() {
        // Arrange
        Product savedProduct = productRepository.save(product);
        int productId = savedProduct.getId();

        // Act
        productRepository.deleteById(productId);

        // Assert
        Optional<Product> deletedProduct = productRepository.findById(productId);
        assertFalse(deletedProduct.isPresent());
    }

    @Test
    @DisplayName("Should delete only the specified product")
    void testDeleteProductByIdSelectiveDelete() {
        // Arrange
        Product saved1 = productRepository.save(product);
        Product saved2 = productRepository.save(product2);

        // Act
        productRepository.deleteById(saved1.getId());

        // Assert
        assertFalse(productRepository.findById(saved1.getId()).isPresent());
        assertTrue(productRepository.findById(saved2.getId()).isPresent());
    }

    @Test
    @DisplayName("Should handle deletion of non-existent product")
    void testDeleteNonExistentProduct() {
        // Act & Assert
        assertDoesNotThrow(() -> productRepository.deleteById(999));
    }

    @Test
    @DisplayName("Should reduce product count after deletion")
    void testDeleteProductCountDecrease() {
        // Arrange
        productRepository.save(product);
        productRepository.save(product2);
        long countBefore = productRepository.count();

        // Act
        List<Product> products = productRepository.findAll();
        if (!products.isEmpty()) {
            productRepository.deleteById(products.get(0).getId());
        }

        // Assert
        long countAfter = productRepository.count();
        assertEquals(countBefore - 1, countAfter);
    }

    // ==================== Tests pour delete ====================

    @Test
    @DisplayName("Should delete product using delete method")
    void testDeleteProduct() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        productRepository.delete(savedProduct);

        // Assert
        assertFalse(productRepository.findById(savedProduct.getId()).isPresent());
    }

    @Test
    @DisplayName("Should delete only specified product")
    void testDeleteProductSelective() {
        // Arrange
        Product saved1 = productRepository.save(product);
        Product saved2 = productRepository.save(product2);

        // Act
        productRepository.delete(saved1);

        // Assert
        assertFalse(productRepository.findById(saved1.getId()).isPresent());
        assertTrue(productRepository.findById(saved2.getId()).isPresent());
    }

    // ==================== Tests pour deleteAll ====================

    @Test
    @DisplayName("Should delete all products")
    void testDeleteAllProducts() {
        // Arrange
        productRepository.save(product);
        productRepository.save(product2);

        // Act
        productRepository.deleteAll();

        // Assert
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    @DisplayName("Should handle deleteAll on empty repository")
    void testDeleteAllEmpty() {
        // Act & Assert
        assertDoesNotThrow(() -> productRepository.deleteAll());
    }

    // ==================== Tests pour count ====================

    @Test
    @DisplayName("Should count products correctly")
    void testCountProducts() {
        // Arrange
        productRepository.save(product);
        productRepository.save(product2);

        // Act
        long count = productRepository.count();

        // Assert
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Should return zero count when no products exist")
    void testCountProductsEmpty() {
        // Act
        long count = productRepository.count();

        // Assert
        assertEquals(0, count);
    }

    @Test
    @DisplayName("Should update count after save")
    void testCountAfterSave() {
        // Arrange
        long initialCount = productRepository.count();

        // Act
        productRepository.save(product);

        // Assert
        long newCount = productRepository.count();
        assertEquals(initialCount + 1, newCount);
    }

    @Test
    @DisplayName("Should decrease count after delete")
    void testCountAfterDelete() {
        // Arrange
        Product savedProduct = productRepository.save(product);
        long countBefore = productRepository.count();

        // Act
        productRepository.delete(savedProduct);

        // Assert
        long countAfter = productRepository.count();
        assertEquals(countBefore - 1, countAfter);
    }

    // ==================== Tests pour existsById ====================

    @Test
    @DisplayName("Should return true when product exists")
    void testExistsByIdTrue() {
        // Arrange
        Product savedProduct = productRepository.save(product);

        // Act
        boolean exists = productRepository.existsById(savedProduct.getId());

        // Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("Should return false when product does not exist")
    void testExistsByIdFalse() {
        // Act
        boolean exists = productRepository.existsById(999);

        // Assert
        assertFalse(exists);
    }

    // ==================== Tests d'intégration du repository ====================

    @Test
    @DisplayName("Should perform complete CRUD cycle with repository")
    void testCompleteCRUDCycle() {
        // Create
        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());

        // Read
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Laptop", foundProduct.get().getName());

        // Update
        foundProduct.get().setName("Updated Laptop");
        foundProduct.get().setPrice(1199.99);
        Product updatedProduct = productRepository.save(foundProduct.get());
        assertEquals("Updated Laptop", updatedProduct.getName());

        // Delete
        productRepository.deleteById(updatedProduct.getId());
        assertFalse(productRepository.findById(updatedProduct.getId()).isPresent());
    }

    @Test
    @DisplayName("Should handle concurrent operations")
    void testConcurrentOperations() {
        // Save multiple products
        Product p1 = productRepository.save(product);
        Product p2 = productRepository.save(product2);

        // Read
        assertTrue(productRepository.findById(p1.getId()).isPresent());
        assertTrue(productRepository.findById(p2.getId()).isPresent());

        // Update one
        p1.setPrice(100.00);
        productRepository.save(p1);

        // Verify other is unchanged
        assertEquals(29.99, productRepository.findById(p2.getId()).get().getPrice());

        // Delete one
        productRepository.deleteById(p1.getId());

        // Verify count
        assertEquals(1, productRepository.count());
    }

    @Test
    @DisplayName("Should persist product data correctly")
    void testDataPersistence() {
        // Arrange
        Product testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(123.45);

        // Act
        Product saved = productRepository.save(testProduct);
        int savedId = saved.getId();

        // Clear context to force reload from database
        productRepository.flush();

        // Assert
        Optional<Product> retrieved = productRepository.findById(savedId);
        assertTrue(retrieved.isPresent());
        assertEquals("Test Product", retrieved.get().getName());
        assertEquals("Test Description", retrieved.get().getDescription());
        assertEquals(123.45, retrieved.get().getPrice());
    }
}

