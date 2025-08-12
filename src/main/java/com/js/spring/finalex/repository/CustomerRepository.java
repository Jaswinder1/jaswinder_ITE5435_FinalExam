package com.js.spring.finalex.repository;


import com.js.spring.finalex.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Example of a derived query method:
    // List<Customer> findByLastName(String lastName);
}