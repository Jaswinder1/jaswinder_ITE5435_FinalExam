package com.js.spring.finalex.repository;


import com.js.spring.finalex.model.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    // Example:
    // List<Payment> findByAmountGreaterThan(double minAmount);
}