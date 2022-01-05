package com.example.demo.services;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductId;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {


    private ProductRepository repository;

    public ProductServices(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> listProduct() {
        return repository.list();
    }


    public Product createProduct(Product product) {
        repository.create(product);
        return product;
    }


    public Product getProducts(ProductId productId) {
        return repository.findOne(productId);
    }


    public void deleteProduct(ProductId productId) {
        repository.delete(productId);
    }


    public Product updateProduct(ProductId productId, Product product) {
        repository.update(productId, product);

        return repository.findOne(productId);
    }

}
