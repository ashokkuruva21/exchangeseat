package com.booktrain.exachangeseat.service;

import com.booktrain.exachangeseat.dto.BookingsDTO;
import com.booktrain.exachangeseat.entity.Bookings;
import com.booktrain.exachangeseat.exception.ResourceNotFoundException;
import com.booktrain.exachangeseat.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingsRepository bookingsRepository;

    public Bookings addBookings(BookingsDTO bookingsDTO){
        Bookings bookings = new Bookings();
        bookings.setTrainNumber(bookingsDTO.getTrainNumber());
        bookings.setDateOfJourney(bookingsDTO.getDateOfJourney());
        bookings.setClassStatus(bookingsDTO.getClassStatus());
        bookings.setBoardingPoint(bookingsDTO.getBoardingPoint());
        bookings.setDestination(bookingsDTO.getDestination());
        bookings.setBookingChannel(bookingsDTO.getBookingChannel());
        bookings.setBookingStatus(bookingsDTO.getBookingStatus());
        bookings.setCurrentTicketStatus(bookingsDTO.getCurrentTicketStatus());
        bookings.setTicketPrice(bookingsDTO.getTicketPrice());

        return bookingsRepository.save(bookings);
    }

    public Bookings getBookingsByBookingId(Long bookingId){
        Optional<Bookings> bookingsById = bookingsRepository.findById(bookingId);
        if(bookingsById.isPresent()){
            return bookingsById.get();
        }
        else{
            throw new ResourceNotFoundException("Booking Record is not found with bookingId "+bookingId);
        }
    }

    public Bookings updateBookings(Bookings bookings, Long bookingId){
        Bookings currentBookingsRecord = this.getBookingsByBookingId(bookingId);
        currentBookingsRecord.setTrainNumber(bookings.getTrainNumber());
        currentBookingsRecord.setDateOfJourney(bookings.getDateOfJourney());
        currentBookingsRecord.setClassStatus(bookings.getClassStatus());
        currentBookingsRecord.setBoardingPoint(bookings.getBoardingPoint());
        currentBookingsRecord.setDestination(bookings.getDestination());
        currentBookingsRecord.setBookingChannel(bookings.getBookingChannel());
        currentBookingsRecord.setBookingStatus(bookings.getBookingStatus());
        currentBookingsRecord.setCurrentTicketStatus(bookings.getCurrentTicketStatus());
        currentBookingsRecord.setTicketPrice(bookings.getTicketPrice());

        return bookingsRepository.save(currentBookingsRecord);
    }

    public String deleteBookings(Long bookingId){
        this.getBookingsByBookingId(bookingId);
        bookingsRepository.deleteById(bookingId);
        return "Bookings record is deleted successfully with bookingId "+bookingId;
    }

    public List<Bookings> getAllBookings(){
        return bookingsRepository.findAll();
    }
}
