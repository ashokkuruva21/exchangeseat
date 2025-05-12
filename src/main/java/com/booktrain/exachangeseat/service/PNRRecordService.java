package com.booktrain.exachangeseat.service;

import com.booktrain.exachangeseat.entity.Bookings;
import com.booktrain.exachangeseat.entity.PNRRecord;
import com.booktrain.exachangeseat.entity.User;
import com.booktrain.exachangeseat.exception.ExchangeNotPossibleException;
import com.booktrain.exachangeseat.exception.ResourceNotFoundException;
import com.booktrain.exachangeseat.repository.PNRRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PNRRecordService {

    private static Long currentPNRvalue = 5000000000L;
    
    @Autowired
    private PNRRecordRepository pnrRecordRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookingService bookingService;
    
    public PNRRecord addPNRRecord(Long userId,Long bookingId){
        User userById = userService.getUserById(userId);
        Bookings bookingsByBookingId = bookingService.getBookingsByBookingId(bookingId);

        PNRRecord pnrRecord = new PNRRecord();
        pnrRecord.setPNRNumber(++currentPNRvalue);
        pnrRecord.setUser(userById);
        pnrRecord.setBookings(bookingsByBookingId);

        return pnrRecordRepository.save(pnrRecord);
    }

    public PNRRecord getByPNRNumber(Long pnrNumber){
        Optional<PNRRecord> byPNRNumber = pnrRecordRepository.getByPNRNumber(pnrNumber);
        if (byPNRNumber.isEmpty()){
            throw new ResourceNotFoundException("PNRNumber " +pnrNumber+ " is Invalid!!!");
        }
        return byPNRNumber.get();
    }

//    public PNRRecord updatePNRRecordForExchange(PNRRecord requestedPNR, PNRRecord proposedPNR){
//
//        requestedPNR.getBookings().setCurrentTicketStatus(proposedPNR.getBookings().getCurrentTicketStatus());
//
//        return pnrRecordRepository.save(requestedPNR);
//    }


}
