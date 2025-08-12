package com.js.spring.finalex.Controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.js.spring.finalex.model.Customer;
import com.js.spring.finalex.model.Payment;
import com.js.spring.finalex.model.Reservation;
import com.js.spring.finalex.repository.CustomerRepository;
import com.js.spring.finalex.repository.PaymentRepository;
import com.js.spring.finalex.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;






@Controller
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    @GetMapping("/reservation")
    public String showForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("customer", new Customer());
        model.addAttribute("payment", new Payment());
        return "reservation-form";
    }

    @PostMapping("/reservation")
    @ResponseBody
    public String submitForm(@ModelAttribute Reservation reservation, @ModelAttribute Customer customer, @ModelAttribute Payment payment) {
        reservationRepo.save(reservation);
        customer.setReservation(reservation.getId());
        customerRepo.save(customer);
        paymentRepo.save(payment);
        
        JsonMapper mapper = new JsonMapper();
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("reservationId", reservation.getId());
        try {
            return mapper.writeValueAsString(response);
        } catch (Exception e) {
            return "{\"status\": \"error\", \"message\": \"JSON conversion failed\"}";
        }
    }
}