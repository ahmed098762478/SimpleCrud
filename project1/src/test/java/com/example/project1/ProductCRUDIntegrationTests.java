package com.example.project1;

import com.example.project1.entity.Product;
import com.example.project1.repository.ProductRepository;
import com.example.project1.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Product CRUD Integration Tests")
class ProductCRUDIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    // ==================== Tests d'intégration CRUD complète ====================

    @Test
    @DisplayName("Should complete full CRUD cycle via API")
    void testFullCRUDCycleViaAPI() throws Exception {
        // 1. CREATE - Créer un nouveau produit
        String productJson = """
                {
                    "name": "Laptop",
                    "description": "High-performance laptop",
                    "price": 999.99
                }
                """;

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());

        // Vérifier que le produit est créé en base de données
        assertEquals(1, productRepository.count());
        Product createdProduct = productRepository.findAll().get(0);
        int productId = createdProduct.getId();

        // 2. READ - Lire le produit créé
        mockMvc.perform(get("/api/product/" + productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Laptop")))
                .andExpect(jsonPath("$.description", is("High-performance laptop")))
                .andExpect(jsonPath("$.price", is(999.99)));

        // 3. UPDATE - Mettre à jour le produit
        String updateJson = """
                {
                    "name": "Laptop Pro",
                    "description": "Premium laptop with high performance",
                    "price": 1299.99
                }
                """;

        mockMvc.perform(put("/api/product/" + productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Laptop Pro")))
                .andExpect(jsonPath("$.price", is(1299.99)));

        // Vérifier la mise à jour en base de données
        Product updatedProduct = productRepository.findById(productId).get();
        assertEquals("Laptop Pro", updatedProduct.getName());
        assertEquals(1299.99, updatedProduct.getPrice());

        // 4. DELETE - Supprimer le produit
        mockMvc.perform(delete("/api/product/" + productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Vérifier la suppression en base de données
        assertTrue(productRepository.findById(productId).isEmpty());
        assertEquals(0, productRepository.count());
    }

    // ==================== Tests de création multiple ====================

    @Test
    @DisplayName("Should create multiple products and retrieve them")
    void testCreateMultipleProducts() throws Exception {
        // Créer plusieurs produits
        String laptop = """
                {
                    "name": "Laptop",
                    "description": "High-performance laptop",
                    "price": 999.99
                }
                """;

        String mouse = """
                {
                    "name": "Mouse",
                    "description": "Wireless mouse",
                    "price": 29.99
                }
                """;

        String keyboard = """
                {
                    "name": "Keyboard",
                    "description": "Mechanical keyboard",
                    "price": 89.99
                }
                """;

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(laptop))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mouse))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(keyboard))
                .andExpect(status().isOk());

        // Vérifier que tous les produits sont créés
        assertEquals(3, productRepository.count());

        // Récupérer tous les produits
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Laptop")))
                .andExpect(jsonPath("$[1].name", is("Mouse")))
                .andExpect(jsonPath("$[2].name", is("Keyboard")));
    }

    // ==================== Tests de validation de données ====================

    @Test
    @DisplayName("Should handle product creation with all valid fields")
    void testCreateProductWithValidData() throws Exception {
        String productJson = """
                {
                    "name": "Monitor",
                    "description": "4K Monitor",
                    "price": 399.99
                }
                """;

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());

        Product savedProduct = productRepository.findAll().get(0);
        assertEquals("Monitor", savedProduct.getName());
        assertEquals("4K Monitor", savedProduct.getDescription());
        assertEquals(399.99, savedProduct.getPrice());
    }

    // ==================== Tests de mise à jour ====================

    @Test
    @DisplayName("Should update only specific fields")
    void testPartialUpdate() throws Exception {
        // Créer un produit
        String createJson = """
                {
                    "name": "Original Product",
                    "description": "Original description",
                    "price": 100.00
                }
                """;

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson))
                .andExpect(status().isOk());

        Product created = productRepository.findAll().get(0);
        int productId = created.getId();

        // Mettre à jour
        String updateJson = """
                {
                    "name": "Updated Product",
                    "description": "Updated description",
                    "price": 150.00
                }
                """;

        mockMvc.perform(put("/api/product/" + productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk());

        // Vérifier
        Product updated = productRepository.findById(productId).get();
        assertEquals("Updated Product", updated.getName());
        assertEquals("Updated description", updated.getDescription());
        assertEquals(150.00, updated.getPrice());
    }

    // ==================== Tests de suppression ====================

    @Test
    @DisplayName("Should delete specific product and keep others")
    void testSelectiveDelete() throws Exception {
        // Créer deux produits
        String product1Json = """
                {
                    "name": "Product 1",
                    "description": "Description 1",
                    "price": 50.00
                }
                """;

        String product2Json = """
                {
                    "name": "Product 2",
                    "description": "Description 2",
                    "price": 75.00
                }
                """;

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(product1Json))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(product2Json))
                .andExpect(status().isOk());

        assertEquals(2, productRepository.count());

        // Supprimer le premier
        Product firstProduct = productRepository.findAll().get(0);
        mockMvc.perform(delete("/api/product/" + firstProduct.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Vérifier
        assertEquals(1, productRepository.count());
        assertTrue(productRepository.findAll().get(0).getName().contains("Product 2"));
    }

    // ==================== Tests de récupération ====================

    @Test
    @DisplayName("Should retrieve product by id correctly")
    void testGetProductById() throws Exception {
        // Créer un produit
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(123.45);
        Product saved = productRepository.save(product);

        // Récupérer via API
        mockMvc.perform(get("/api/product/" + saved.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(saved.getId())))
                .andExpect(jsonPath("$.name", is("Test Product")))
                .andExpect(jsonPath("$.description", is("Test Description")))
                .andExpect(jsonPath("$.price", is(123.45)));
    }

    @Test
    @DisplayName("Should handle retrieval of non-existent product")
    void testGetNonExistentProduct() throws Exception {
        mockMvc.perform(get("/api/product/9999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // ==================== Tests de gestion des listes ====================

    @Test
    @DisplayName("Should return empty list when no products exist")
    void testGetAllProductsEmpty() throws Exception {
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ==================== Tests de cohérence des données ====================

    @Test
    @DisplayName("Should maintain data consistency through multiple operations")
    void testDataConsistency() throws Exception {
        // Créer
        Product p1 = new Product();
        p1.setName("Product 1");
        p1.setPrice(100.00);
        Product saved1 = productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Product 2");
        p2.setPrice(200.00);
        Product saved2 = productRepository.save(p2);

        // Vérifier via getAllProducts
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        // Mettre à jour le premier
        saved1.setPrice(150.00);
        productService.updateProduct(saved1.getId(), saved1);

        // Vérifier que le second n'a pas changé
        Product verify = productRepository.findById(saved2.getId()).get();
        assertEquals(200.00, verify.getPrice());

        // Supprimer le premier
        productService.deleteProduct(saved1.getId());

        // Vérifier que seul le second reste
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Product 2")));
    }

    // ==================== Tests de performance ====================

    @Test
    @DisplayName("Should handle bulk operations efficiently")
    void testBulkOperations() throws Exception {
        // Créer 10 produits
        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("Description " + i);
            product.setPrice(10.0 * i);

            mockMvc.perform(post("/api/product")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(product)))
                    .andExpect(status().isOk());
        }

        // Vérifier le count
        assertEquals(10, productRepository.count());

        // Récupérer tous
        mockMvc.perform(get("/api/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }
}



