package com.booktrain.exachangeseat.controller;

import com.booktrain.exachangeseat.dto.PNRRequestDAO;
import com.booktrain.exachangeseat.entity.ExchangePNR;
import com.booktrain.exachangeseat.entity.PNRRecord;
import com.booktrain.exachangeseat.service.ExchangePNRService;
import com.booktrain.exachangeseat.service.PNRRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PNR")
public class PNRExchangeController {

    @Autowired
    private ExchangePNRService exchangePNRService;

    @Autowired
    private PNRRecordService pnrRecordService;


    @PostMapping("/book/{userId}/{bookingId}")
    public ResponseEntity<PNRRecord> addPNRRecord(@PathVariable Long userId,@PathVariable Long bookingId){
        PNRRecord pnrRecord = pnrRecordService.addPNRRecord(userId, bookingId);
        return new ResponseEntity<>(pnrRecord,HttpStatus.OK);
    }

    @PostMapping("/request/{PNRNumber}")
    public ResponseEntity<ExchangePNR> requestExchange(@PathVariable Long PNRNumber){
        ExchangePNR exchangePNR = exchangePNRService.requestExchange(PNRNumber);
        return new ResponseEntity<>(exchangePNR, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PNRRequestDAO>> getAllInProgressExchangeRequests(){
        List<PNRRequestDAO> allInProgressExchangeRequests = exchangePNRService.getAllInProgressExchangeRequests();
        return new ResponseEntity<>(allInProgressExchangeRequests,HttpStatus.OK);
    }

    @PutMapping("/acceptanceProposal/{requestedPNRNumber}/{proposedPNRNumber}")
    public ResponseEntity<ExchangePNR> proposeAcceptanceOfRequest(@PathVariable Long requestedPNRNumber,@PathVariable Long proposedPNRNumber){
        ExchangePNR exchangePNR = exchangePNRService.proposeAcceptanceOfRequest(requestedPNRNumber, proposedPNRNumber);
        return new ResponseEntity<>(exchangePNR,HttpStatus.OK);
    }

    @PutMapping("/agree/{requestedPNRNumber}/{proposedPNRNumber}")
    public ResponseEntity<ExchangePNR> acceptAgreedProposal(@PathVariable Long requestedPNRNumber,@PathVariable Long proposedPNRNumber){
        ExchangePNR exchangePNR = exchangePNRService.acceptAgreedProposal(requestedPNRNumber, proposedPNRNumber);
        return new ResponseEntity<>(exchangePNR,HttpStatus.OK);
    }

    @PutMapping("/exchange/{requestedPNRNumber}/{proposedPNRNumber}")
    public ResponseEntity<ExchangePNR> exchangePNR(@PathVariable Long requestedPNRNumber,@PathVariable Long proposedPNRNumber){
        ExchangePNR exchangedPNR = exchangePNRService.exchangePNR(requestedPNRNumber, proposedPNRNumber);
        return new ResponseEntity<>(exchangedPNR,HttpStatus.OK);
    }

    @PutMapping("/cancel/{requestedPNRNumber}/{proposedPNRNumber}")
    public ResponseEntity<ExchangePNR> cancelPNR(@PathVariable Long requestedPNRNumber,@PathVariable Long proposedPNRNumber){
        ExchangePNR cancelledPNR = exchangePNRService.cancelExchange(requestedPNRNumber,proposedPNRNumber);
        return new ResponseEntity<>(cancelledPNR,HttpStatus.OK);
    }

}
