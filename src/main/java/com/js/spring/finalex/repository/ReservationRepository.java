package com.js.spring.finalex.repository;


import com.js.spring.finalex.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    // You can define custom query methods if needed, e.g.:
    // List<Reservation> findByTicketNumber(String ticketNumber);
}