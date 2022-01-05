package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private final ProductId id_number;
    private final ProductName name;
    private final LocalDate birthday;

    public Product(ProductId id_number, ProductName name, LocalDate birthday) {
        Objects.requireNonNull(id_number, "Product id can not be null");
        Objects.requireNonNull(name, "Product name can not be null");
        Objects.requireNonNull(birthday, "Product birthday can not be null");
        this.id_number = id_number;
        this.name = name;
        this.birthday = birthday;
    }

    public ProductId getId() {
        return id_number;
    }

    public ProductName getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id_number +
                ", name=" + name +
                '}';
    }
}
