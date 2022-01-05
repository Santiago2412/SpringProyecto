package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductId;
import com.example.demo.domain.ProductName;
import com.example.demo.model.CreateProductInput;
import com.example.demo.model.CreateProductOutput;
import com.example.demo.model.UpdateProductInput;
import com.example.demo.model.UpdateProductOutput;
import com.example.demo.services.ProductServices;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductServices services;

    public ProductController(ProductServices services) {
        this.services = services;
    }


    @GetMapping
    public List<Product> listProduct() {
        return services.listProduct();
    }

    @PostMapping(value = "/products")
    public CreateProductOutput createProduct(@RequestBody CreateProductInput input) {
        ProductName productName = new ProductName(input.getName());
        LocalDate birthday = input.getBirthday();
        ProductId random = ProductId.random();
        Product product = new Product(random, productName, birthday);
        Product createdProduct = services.createProduct(product);

        return new CreateProductOutput(createdProduct);
    }


    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") String productId) {
        final ProductId id = ProductId.fromString(productId);
        return services.getProducts(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") String productId) {
        final ProductId id = ProductId.fromString(productId);
        services.deleteProduct(id);
    }

    @PutMapping(value = "/{id}")
    public UpdateProductOutput updateProduct(@PathVariable("id") String unsafeId, @RequestBody UpdateProductInput input) {
        final ProductId id = ProductId.fromString(unsafeId);
        Product newProduct = new Product(id, new ProductName(input.getName()), input.getBirthday());
        final Product updated = services.updateProduct(id, newProduct);
        return new UpdateProductOutput(updated);
    }
}
// En el postman, cada requerimiento que se hace, nos arroja lo que está pasando en el cuadro donde corremos el código