package com.example.project1.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Entity Tests")
class ProductTests {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    // ==================== Tests pour les getters et setters ====================

    @Test
    @DisplayName("Should set and get id correctly")
    void testSetAndGetId() {
        // Act
        product.setId(1);

        // Assert
        assertEquals(1, product.getId());
    }

    @Test
    @DisplayName("Should set and get name correctly")
    void testSetAndGetName() {
        // Act
        product.setName("Laptop");

        // Assert
        assertEquals("Laptop", product.getName());
    }

    @Test
    @DisplayName("Should set and get description correctly")
    void testSetAndGetDescription() {
        // Act
        product.setDescription("High-performance laptop");

        // Assert
        assertEquals("High-performance laptop", product.getDescription());
    }

    @Test
    @DisplayName("Should set and get price correctly")
    void testSetAndGetPrice() {
        // Act
        product.setPrice(999.99);

        // Assert
        assertEquals(999.99, product.getPrice());
    }

    // ==================== Tests de validation ====================

    @Test
    @DisplayName("Should accept valid product data")
    void testValidProductData() {
        // Act
        product.setId(1);
        product.setName("Mouse");
        product.setDescription("Wireless mouse");
        product.setPrice(29.99);

        // Assert
        assertEquals(1, product.getId());
        assertEquals("Mouse", product.getName());
        assertEquals("Wireless mouse", product.getDescription());
        assertEquals(29.99, product.getPrice());
    }

    @Test
    @DisplayName("Should handle null name")
    void testNullName() {
        // Act
        product.setName(null);

        // Assert
        assertNull(product.getName());
    }

    @Test
    @DisplayName("Should handle null description")
    void testNullDescription() {
        // Act
        product.setDescription(null);

        // Assert
        assertNull(product.getDescription());
    }

    @Test
    @DisplayName("Should handle zero price")
    void testZeroPrice() {
        // Act
        product.setPrice(0.0);

        // Assert
        assertEquals(0.0, product.getPrice());
    }

    @Test
    @DisplayName("Should handle negative price")
    void testNegativePrice() {
        // Act
        product.setPrice(-10.0);

        // Assert
        assertEquals(-10.0, product.getPrice());
    }

    @Test
    @DisplayName("Should handle large price values")
    void testLargePrice() {
        // Act
        product.setPrice(9999999.99);

        // Assert
        assertEquals(9999999.99, product.getPrice());
    }

    // ==================== Tests d'intégrité des données ====================

    @Test
    @DisplayName("Should update all fields independently")
    void testIndependentFieldUpdates() {
        // Act
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(100.0);

        product.setName("Product 1 Updated");

        // Assert
        assertEquals(1, product.getId());
        assertEquals("Product 1 Updated", product.getName());
        assertEquals(100.0, product.getPrice());
    }

    @Test
    @DisplayName("Should handle empty string values")
    void testEmptyStrings() {
        // Act
        product.setName("");
        product.setDescription("");

        // Assert
        assertEquals("", product.getName());
        assertEquals("", product.getDescription());
    }

    @Test
    @DisplayName("Should handle special characters in strings")
    void testSpecialCharacters() {
        // Act
        product.setName("Product @#$%");
        product.setDescription("Description with 'quotes' and \"double quotes\"");

        // Assert
        assertEquals("Product @#$%", product.getName());
        assertEquals("Description with 'quotes' and \"double quotes\"", product.getDescription());
    }

    @Test
    @DisplayName("Should handle unicode characters")
    void testUnicodeCharacters() {
        // Act
        product.setName("笔记本电脑");
        product.setDescription("Description 日本語");

        // Assert
        assertEquals("笔记本电脑", product.getName());
        assertEquals("Description 日本語", product.getDescription());
    }

    // ==================== Tests de types de données ====================

    @Test
    @DisplayName("Should maintain price precision")
    void testPricePrecision() {
        // Act
        product.setPrice(99.999);

        // Assert
        assertEquals(99.999, product.getPrice());
    }

    @Test
    @DisplayName("Should handle very small decimal values")
    void testSmallDecimalPrice() {
        // Act
        product.setPrice(0.01);

        // Assert
        assertEquals(0.01, product.getPrice());
    }

    // ==================== Tests d'état de l'objet ====================

    @Test
    @DisplayName("Should create new product with uninitialized fields")
    void testNewProductUninitializedFields() {
        // Arrange
        Product newProduct = new Product();

        // Assert
        assertEquals(0, newProduct.getId()); // int default value
        assertNull(newProduct.getName());
        assertNull(newProduct.getDescription());
        assertEquals(0.0, newProduct.getPrice()); // double default value
    }

    @Test
    @DisplayName("Should allow multiple instances with different data")
    void testMultipleInstances() {
        // Arrange
        Product product1 = new Product();
        Product product2 = new Product();

        // Act
        product1.setName("Product 1");
        product1.setPrice(100.0);

        product2.setName("Product 2");
        product2.setPrice(200.0);

        // Assert
        assertEquals("Product 1", product1.getName());
        assertEquals("Product 2", product2.getName());
        assertEquals(100.0, product1.getPrice());
        assertEquals(200.0, product2.getPrice());
    }

    @Test
    @DisplayName("Should change values correctly after initialization")
    void testValueChanges() {
        // Arrange
        product.setName("Original Name");
        product.setPrice(100.0);

        // Act
        product.setName("New Name");
        product.setPrice(200.0);

        // Assert
        assertEquals("New Name", product.getName());
        assertEquals(200.0, product.getPrice());
    }

    @Test
    @DisplayName("Should maintain consistency across multiple get calls")
    void testGetConsistency() {
        // Arrange
        product.setName("Test Product");
        product.setPrice(99.99);

        // Act & Assert
        assertEquals(product.getName(), product.getName());
        assertEquals(product.getPrice(), product.getPrice());
        assertEquals(product.getDescription(), product.getDescription());
    }
}

