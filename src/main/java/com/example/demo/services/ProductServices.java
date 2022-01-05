package com.example.demo.services;

import com.example.demo.domain.Product;
import com.example.demo.domain.ProductId;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServices {


    private ProductRepository repository;

    public PersonServices(ProductRepository repository) {
        this.repository = repository;
    }


    public List<Product> listPersons() {
        return repository.list();
    }


    public Product createPerson(Product person) {
        repository.create(person);
        return person;
    }


    public Product getPerson(ProductId personId) {
        return repository.findOne(personId);
    }


    public void deletePerson(ProductId personId) {
        repository.delete(personId);
    }


    public Product updatePerson(ProductId personId, Product person) {
        repository.update(personId, person);

        return repository.findOne(personId);
    }

}
