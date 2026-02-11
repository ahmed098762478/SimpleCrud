package com.example.project1.controller;

import com.example.project1.entity.Product;
import com.example.project1.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Product Controller Tests")
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;
    private Product product2;

    @BeforeEach
    void setUp() {
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

    // ==================== Tests pour GET /api/product ====================

    @Test
    @DisplayName("Should return all products with status 200")
    void testGetAllProducts() throws Exception {
        // Arrange
        List<Product> products = Arrays.asList(product, product2);
        when(productService.getAllProducts()).thenReturn(products);

        // Act & Assert
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Laptop")))
                .andExpect(jsonPath("$[0].price", is(999.99)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Mouse")));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    @DisplayName("Should return empty list when no products exist")
    void testGetAllProductsEmpty() throws Exception {
        // Arrange
        when(productService.getAllProducts()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(productService, times(1)).getAllProducts();
    }

    // ==================== Tests pour GET /api/product/{id} ====================

    @Test
    @DisplayName("Should return product with status 200 when product exists")
    void testGetProductByIdSuccess() throws Exception {
        // Arrange
        when(productService.getProductById(1)).thenReturn(Optional.of(product));

        // Act & Assert
        mockMvc.perform(get("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Laptop")))
                .andExpect(jsonPath("$.description", is("High-performance laptop")))
                .andExpect(jsonPath("$.price", is(999.99)));

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    @DisplayName("Should return null when product does not exist")
    void testGetProductByIdNotFound() throws Exception {
        // Arrange
        when(productService.getProductById(999)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/product/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).getProductById(999);
    }

    @Test
    @DisplayName("Should call service with correct product id")
    void testGetProductByIdWithDifferentIds() throws Exception {
        // Arrange
        Product product3 = new Product();
        product3.setId(5);
        product3.setName("Keyboard");
        product3.setPrice(89.99);

        when(productService.getProductById(5)).thenReturn(Optional.of(product3));

        // Act & Assert
        mockMvc.perform(get("/api/product/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.name", is("Keyboard")));

        verify(productService, times(1)).getProductById(5);
    }

    // ==================== Tests pour POST /api/product ====================

    @Test
    @DisplayName("Should create product with status 200")
    void testCreateProductSuccess() throws Exception {
        // Arrange
        Product newProduct = new Product();
        newProduct.setName("Monitor");
        newProduct.setDescription("4K Monitor");
        newProduct.setPrice(399.99);

        Product createdProduct = new Product();
        createdProduct.setId(3);
        createdProduct.setName("Monitor");
        createdProduct.setDescription("4K Monitor");
        createdProduct.setPrice(399.99);

        when(productService.CreateProduct(any(Product.class))).thenReturn(createdProduct);

        // Act & Assert
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk());

        verify(productService, times(1)).CreateProduct(any(Product.class));
    }

    @Test
    @DisplayName("Should accept valid product data")
    void testCreateProductWithValidData() throws Exception {
        // Arrange
        String productJson = """
                {
                    "name": "Headphones",
                    "description": "Wireless headphones",
                    "price": 149.99
                }
                """;

        Product expectedProduct = new Product();
        expectedProduct.setId(4);
        expectedProduct.setName("Headphones");
        expectedProduct.setDescription("Wireless headphones");
        expectedProduct.setPrice(149.99);

        when(productService.CreateProduct(any(Product.class))).thenReturn(expectedProduct);

        // Act & Assert
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());

        verify(productService, times(1)).CreateProduct(any(Product.class));
    }

    @Test
    @DisplayName("Should handle product creation with missing fields")
    void testCreateProductWithMinimalData() throws Exception {
        // Arrange
        String productJson = """
                {
                    "name": "Product",
                    "price": 50.00
                }
                """;

        when(productService.CreateProduct(any(Product.class))).thenReturn(new Product());

        // Act & Assert
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());

        verify(productService, times(1)).CreateProduct(any(Product.class));
    }

    // ==================== Tests pour PUT /api/product/{id} ====================

    @Test
    @DisplayName("Should update product with status 200")
    void testUpdateProductSuccess() throws Exception {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setName("Laptop Pro");
        updatedProduct.setDescription("Premium laptop");
        updatedProduct.setPrice(1299.99);

        Product resultProduct = new Product();
        resultProduct.setId(1);
        resultProduct.setName("Laptop Pro");
        resultProduct.setDescription("Premium laptop");
        resultProduct.setPrice(1299.99);

        when(productService.updateProduct(eq(1), any(Product.class))).thenReturn(resultProduct);

        // Act & Assert
        mockMvc.perform(put("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Laptop Pro")))
                .andExpect(jsonPath("$.price", is(1299.99)));

        verify(productService, times(1)).updateProduct(eq(1), any(Product.class));
    }

    @Test
    @DisplayName("Should return null when updating non-existent product")
    void testUpdateProductNotFound() throws Exception {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setName("Non-existent");
        updatedProduct.setPrice(99.99);

        when(productService.updateProduct(eq(999), any(Product.class))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/api/product/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(eq(999), any(Product.class));
    }

    @Test
    @DisplayName("Should update product with different id values")
    void testUpdateProductWithDifferentIds() throws Exception {
        // Arrange
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated");
        updatedProduct.setPrice(150.00);

        Product resultProduct = new Product();
        resultProduct.setId(5);
        resultProduct.setName("Updated");
        resultProduct.setPrice(150.00);

        when(productService.updateProduct(eq(5), any(Product.class))).thenReturn(resultProduct);

        // Act & Assert
        mockMvc.perform(put("/api/product/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)));

        verify(productService, times(1)).updateProduct(eq(5), any(Product.class));
    }

    // ==================== Tests pour DELETE /api/product/{id} ====================

    @Test
    @DisplayName("Should delete product with status 200")
    void testDeleteProductSuccess() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(1);
    }

    @Test
    @DisplayName("Should call delete with correct product id")
    void testDeleteProductWithDifferentIds() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/product/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(5);
    }

    @Test
    @DisplayName("Should handle deletion of non-existent product")
    void testDeleteNonExistentProduct() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/product/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(999);
    }

    // ==================== Tests d'intégration du contrôleur ====================

    @Test
    @DisplayName("Should handle multiple requests in sequence")
    void testMultipleRequests() throws Exception {
        // Get all
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Get by id
        when(productService.getProductById(1)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Create
        when(productService.CreateProduct(any(Product.class))).thenReturn(product2);
        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product2)))
                .andExpect(status().isOk());

        // Update
        when(productService.updateProduct(eq(1), any(Product.class))).thenReturn(product);
        mockMvc.perform(put("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        // Delete
        mockMvc.perform(delete("/api/product/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should handle CORS requests")
    void testCORSHeaders() throws Exception {
        // Arrange
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));

        // Act & Assert
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}



