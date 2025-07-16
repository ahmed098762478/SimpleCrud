package com.example.project1.service;

import com.example.project1.entity.Product;
import com.example.project1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

    public Product CreateProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(int id,Product updateProduct){
    return  productRepository.findById(id).map(product->{
     product.setName(updateProduct.getName());
     product.setDescription(updateProduct.getDescription());
     product.setPrice(updateProduct.getPrice());
     return productRepository.save(product);

    }).orElse(null);
    }

    public void deleteProduct(int id){
         productRepository.deleteById(id);
    }




}
