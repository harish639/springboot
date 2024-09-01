package com.example.project.Service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.Model.Product;
import com.example.project.Repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Add a new product
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Update a product
    public Product updateProduct(Product product, int id) {
        Product existingProduct = productRepo.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Product not found with id " + id));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setFeedback(product.getFeedback());
        return productRepo.save(existingProduct);
    }

    // Delete a product
    public void deleteProduct(int id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
        } else {
            throw new NoSuchElementException("Product not found with id " + id);
        }
    }
}
