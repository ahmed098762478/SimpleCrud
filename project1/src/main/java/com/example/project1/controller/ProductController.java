package com.example.project1.controller;

import com.example.project1.entity.Product;
import com.example.project1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

       @Autowired
        private ProductService productService;

       @GetMapping
     public List<Product> getALlProducts(){
           return productService.getAllProducts();
       }

       @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
           return productService.getProductById(id).orElse(null);
       }

       @PostMapping
    public void  createProduct(@RequestBody Product product){
           productService.CreateProduct(product);
       }

       @PutMapping("/{id}")
    public Product upadateProduct(@PathVariable int id,@RequestBody Product product){
           return productService.updateProduct(id,product);
       }

       @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
           productService.deleteProduct(id);
       }


}
