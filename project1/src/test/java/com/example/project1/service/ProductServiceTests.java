package com.example.project1.service;

import com.example.project1.entity.Product;
import com.example.project1.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Product Service Tests")
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des produits de test
        product = new Product();
        product.setId(1);
        product.setName("Laptop");
        product.setDescription("High-performance laptop");
        product.setPrice(999.99);

        product2 = new Product();
        product2.setId(2);
        product2.setName("Mouse");
        product2.setDescription("Wireless mouse");
        product2.setPrice(29.99);
    }

    // ==================== Tests pour getAllProducts ====================

    @Test
    @DisplayName("Should return all products when getAllProducts is called")
    void testGetAllProducts() {
        // Arrange
        List<Product> products = Arrays.asList(product, product2);
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Mouse", result.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no products exist")
    void testGetAllProductsEmpty() {
        // Arrange
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    // ==================== Tests pour getProductById ====================

    @Test
    @DisplayName("Should return product when getProductById is called with valid id")
    void testGetProductByIdSuccess() {
        // Arrange
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.getProductById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        assertEquals("Laptop", result.get().getName());
        assertEquals(999.99, result.get().getPrice());
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Should return empty Optional when product not found")
    void testGetProductByIdNotFound() {
        // Arrange
        when(productRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<Product> result = productService.getProductById(999);

        // Assert
        assertFalse(result.isPresent());
        verify(productRepository, times(1)).findById(999);
    }

    // ==================== Tests pour CreateProduct ====================

    @Test
    @DisplayName("Should create and return product when CreateProduct is called")
    void testCreateProductSuccess() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setName("Keyboard");
        newProduct.setDescription("Mechanical keyboard");
        newProduct.setPrice(89.99);

        Product savedProduct = new Product();
        savedProduct.setId(3);
        savedProduct.setName("Keyboard");
        savedProduct.setDescription("Mechanical keyboard");
        savedProduct.setPrice(89.99);

        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        // Act
        Product result = productService.CreateProduct(newProduct);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals("Keyboard", result.getName());
        assertEquals("Mechanical keyboard", result.getDescription());
        assertEquals(89.99, result.getPrice());
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    @DisplayName("Should save product with all fields populated")
    void testCreateProductWithAllFields() {
        // Arrange
        Product newProduct = new Product();
        newProduct.setName("Monitor");
        newProduct.setDescription("4K Monitor");
        newProduct.setPrice(399.99);

        when(productRepository.save(newProduct)).thenReturn(newProduct);

        // Act
        Product result = productService.CreateProduct(newProduct);

        // Assert
        assertNotNull(result.getName());
        assertNotNull(result.getDescription());
        assertTrue(result.getPrice() > 0);
    }

    // ==================== Tests pour updateProduct ====================

    @Test
    @DisplayName("Should update product when updateProduct is called with valid id")
    void testUpdateProductSuccess() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setName("Laptop Pro");
        updatedProduct.setDescription("Premium laptop with high performance");
        updatedProduct.setPrice(1299.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.updateProduct(1, updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Laptop Pro", result.getName());
        assertEquals("Premium laptop with high performance", result.getDescription());
        assertEquals(1299.99, result.getPrice());
        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    @DisplayName("Should return null when updating non-existent product")
    void testUpdateProductNotFound() {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setName("Non-existent");
        updatedProduct.setDescription("This product doesn't exist");
        updatedProduct.setPrice(99.99);

        when(productRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Product result = productService.updateProduct(999, updatedProduct);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).findById(999);
        verify(productRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should update only changed fields")
    void testUpdateProductPartialFields() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(1);
        existingProduct.setName("Original");
        existingProduct.setDescription("Original description");
        existingProduct.setPrice(100.00);

        Product updateData = new Product();
        updateData.setName("Updated");
        updateData.setDescription("Updated description");
        updateData.setPrice(150.00);

        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product result = productService.updateProduct(1, updateData);

        // Assert
        assertEquals("Updated", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertEquals(150.00, result.getPrice());
    }

    // ==================== Tests pour deleteProduct ====================

    @Test
    @DisplayName("Should delete product when deleteProduct is called")
    void testDeleteProductSuccess() {
        // Act
        productService.deleteProduct(1);

        // Assert
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Should call repository deleteById with correct id")
    void testDeleteProductWithSpecificId() {
        // Act
        productService.deleteProduct(5);

        // Assert
        verify(productRepository, times(1)).deleteById(5);
    }

    @Test
    @DisplayName("Should handle deletion of non-existent product gracefully")
    void testDeleteNonExistentProduct() {
        // Act
        productService.deleteProduct(999);

        // Assert
        verify(productRepository, times(1)).deleteById(999);
    }

    // ==================== Tests d'intégration logique ====================

    @Test
    @DisplayName("Should perform complete CRUD cycle")
    void testCompleteCRUDCycle() {
        // Create
        Product newProduct = new Product();
        newProduct.setName("Test Product");
        newProduct.setDescription("Test Description");
        newProduct.setPrice(50.00);

        Product savedProduct = new Product();
        savedProduct.setId(10);
        savedProduct.setName("Test Product");
        savedProduct.setDescription("Test Description");
        savedProduct.setPrice(50.00);

        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        Product created = productService.CreateProduct(newProduct);
        assertEquals("Test Product", created.getName());

        // Read
        when(productRepository.findById(10)).thenReturn(Optional.of(savedProduct));
        Optional<Product> read = productService.getProductById(10);
        assertTrue(read.isPresent());

        // Update
        Product updateData = new Product();
        updateData.setName("Updated Test Product");
        updateData.setDescription("Updated Description");
        updateData.setPrice(75.00);

        when(productRepository.findById(10)).thenReturn(Optional.of(savedProduct));
        when(productRepository.save(savedProduct)).thenReturn(savedProduct);

        Product updated = productService.updateProduct(10, updateData);
        assertEquals("Updated Test Product", updated.getName());

        // Delete
        productService.deleteProduct(10);
        verify(productRepository).deleteById(10);
    }
}

