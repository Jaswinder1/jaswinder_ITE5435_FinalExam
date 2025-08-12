package com.js.spring.finalex.Controller;

import com.js.spring.finalex.model.Customer;
import com.js.spring.finalex.model.Payment;
import com.js.spring.finalex.model.Reservation;
import com.js.spring.finalex.repository.CustomerRepository;
import com.js.spring.finalex.repository.PaymentRepository;
import com.js.spring.finalex.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    // 1️⃣ CREATE new reservation with customer + payment
    @PostMapping
    public Map<String, Object> createReservation(@RequestBody Map<String, Object> payload) {
        // Parse Reservation
        Map<String, Object> reservationData = (Map<String, Object>) payload.get("reservation");
        Reservation reservation = new Reservation(
                (String) reservationData.get("details"),
                (String) reservationData.get("ticketNumber"),
                (String) reservationData.get("date")
        );
        reservationRepo.save(reservation);

        // Parse Customer
        Map<String, Object> customerData = (Map<String, Object>) payload.get("customer");
        Customer customer = new Customer(
                (String) customerData.get("address"),
                reservation.getId(),
                (String) customerData.get("details"),
                (String) customerData.get("phoneNumber"),
                (String) customerData.get("address"),  // assuming reservation saved and has id
                (String) customerData.get("details")
        );
        customerRepo.save(customer);

        // Parse Payment
        Map<String, Object> paymentData = (Map<String, Object>) payload.get("payment");
        Payment payment = new Payment(
                (int) paymentData.get("amount"),
                (String) paymentData.get("date")
        );
        paymentRepo.save(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("reservationId", reservation.getId());
        return response; // Jackson serializes automatically
    }

    // 2️⃣ GET all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    // 3️⃣ GET one reservation by ID
    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable String id) {
        return reservationRepo.findById(id);
    }

    // 4️⃣ UPDATE reservation
    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation updated) {
        return reservationRepo.findById(id).map(res -> {
            res.setDetails(updated.getDetails());
            res.setTicketNumber(updated.getTicketNumber());
            res.setDate(updated.getDate());
            return reservationRepo.save(res);
        }).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // 5️⃣ DELETE reservation
    @DeleteMapping("/{id}")
    public Map<String, String> deleteReservation(@PathVariable String id) {
        reservationRepo.deleteById(id);
        Map<String, String> res = new HashMap<>();
        res.put("status", "deleted");
        return res;
    }
}