package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    private List<Booking> bookings = new ArrayList<>();

    @GetMapping("/booking")
    public List<Booking> getBookings() {
        return bookings;
    }

    @PostMapping("/booking")
    public ResponseEntity<Object>  createBooking(@RequestBody Booking booking){
        for(Booking b : bookings){
            if(b.getRoomNumber() ==  booking.getRoomNumber()
            && b.getDate().equals(booking.getDate())){
                return ResponseEntity.status(409).body("La chambre " + booking.getRoomNumber() + " est déjà réservée.");
            }
        }
        bookings.add(booking);
        return ResponseEntity.status(200).body(booking);
    }
}
