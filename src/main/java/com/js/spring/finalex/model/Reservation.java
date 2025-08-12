package com.js.spring.finalex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "reservations")
public class Reservation {
    @Id
    private String id;
    private String details;
    private String ticketNumber;
    private String date;

    public Reservation() {}
    
    public Reservation(String details, String ticketNumber, String date) {
        this.details = details;
        this.ticketNumber = ticketNumber;
        this.date = date;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
