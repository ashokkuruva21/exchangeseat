package com.booktrain.exachangeseat.controller;

import com.booktrain.exachangeseat.dto.BookingsDTO;
import com.booktrain.exachangeseat.entity.Bookings;
import com.booktrain.exachangeseat.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<Bookings> addBookings(@RequestBody BookingsDTO bookingsDTO){
        Bookings bookings = bookingService.addBookings(bookingsDTO);
        return new ResponseEntity<>(bookings, HttpStatus.CREATED);
    }

    @GetMapping("/get/{bookingId}")
    public ResponseEntity<Bookings> getBookingsByBookingId(@PathVariable Long bookingId){
        Bookings bookingsByBookingId = bookingService.getBookingsByBookingId(bookingId);
        return new ResponseEntity<>(bookingsByBookingId,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Bookings>> getAllBookings(){
        List<Bookings> allBookings = bookingService.getAllBookings();
        return new ResponseEntity<>(allBookings,HttpStatus.OK);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Bookings> updateBookings(@RequestBody Bookings bookings,@PathVariable Long bookingId){
        Bookings updatedBookings = bookingService.updateBookings(bookings, bookingId);
        return new ResponseEntity<>(updatedBookings,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBookings(@PathVariable Long bookingId){
        String response = bookingService.deleteBookings(bookingId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
