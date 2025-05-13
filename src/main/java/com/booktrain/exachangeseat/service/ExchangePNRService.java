package com.booktrain.exachangeseat.service;

import com.booktrain.exachangeseat.dto.PNRRequestDAO;
import com.booktrain.exachangeseat.entity.ExchangePNR;
import com.booktrain.exachangeseat.entity.ExchangedDetails;
import com.booktrain.exachangeseat.entity.PNRRecord;
import com.booktrain.exachangeseat.exception.ExchangeNotPossibleException;
import com.booktrain.exachangeseat.exception.ResourceNotFoundException;
import com.booktrain.exachangeseat.repository.ExchangePNRRepository;
import com.booktrain.exachangeseat.repository.PNRRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ExchangePNRService {


    @Autowired
    private PNRRecordRepository pnrRecordRepository;

    @Autowired
    private ExchangePNRRepository exchangePNRRepository;

    @Autowired
    private PNRRecordService pnrRecordService;

    @Autowired
    private ExchangedDetailsService exchangedDetailsService;


    public ExchangePNR requestExchange(Long pnrNumber){

        Optional<PNRRecord> byPNRNumber = pnrRecordRepository.getByPNRNumber(pnrNumber);
        if (byPNRNumber.isEmpty()){
            throw new ResourceNotFoundException("Record not found with PNR Number "+pnrNumber+" !! Please enter valid PNR Number");
        }

        if(exchangePNRRepository.getByRequestedPNRNumber(pnrNumber).isPresent()){
            throw new ExchangeNotPossibleException("Seat Exchange with PNRNumber "+pnrNumber+" is already requested!!!");
        }

        ExchangePNR exchangePNR = new ExchangePNR();
        exchangePNR.setRequestedPNRNumber(pnrNumber);
        exchangePNR.setExchangeStatus("REQUESTED");
        return exchangePNRRepository.save(exchangePNR);
    }

    public List<PNRRequestDAO> getAllInProgressExchangeRequests(){
        List<PNRRequestDAO> pnrRequestDAOS = new ArrayList<>();

        List<ExchangePNR> allRecords = exchangePNRRepository.findAll();

        for(ExchangePNR exchangePNR:allRecords){
            Long requestedPNR = exchangePNR.getRequestedPNRNumber();
            PNRRecord byPNRNumber = pnrRecordService.getByPNRNumber(requestedPNR);

            if(! exchangePNR.getExchangeStatus().equals("EXCHANGED")){
                PNRRequestDAO pnrRequestDAO = new PNRRequestDAO();

                pnrRequestDAO.setRequestedPNR(byPNRNumber.getPNRNumber());
                pnrRequestDAO.setTrainNumber(byPNRNumber.getBookings().getTrainNumber());
                pnrRequestDAO.setDateOfJourney(byPNRNumber.getBookings().getDateOfJourney());
                pnrRequestDAO.setClassStatus(byPNRNumber.getBookings().getClassStatus());
                pnrRequestDAO.setCurrentTicketStatus(byPNRNumber.getBookings().getCurrentTicketStatus());
                pnrRequestDAO.setBoardingPoint(byPNRNumber.getBookings().getBoardingPoint());
                pnrRequestDAO.setDestination(byPNRNumber.getBookings().getDestination());

                pnrRequestDAOS.add(pnrRequestDAO);
            }
        }
        return pnrRequestDAOS;
    }


    public ExchangePNR proposeAcceptanceOfRequest(Long requestedPNRNumber,Long proposedPNRNumber){
        PNRRecord requestedPNRRecord = pnrRecordService.getByPNRNumber(requestedPNRNumber);

        PNRRecord proposedPNRRecord = pnrRecordService.getByPNRNumber(proposedPNRNumber);

        List<ExchangePNR> allByRequestedPNRNumber = exchangePNRRepository.getAllByRequestedPNRNumber(requestedPNRNumber);
        if(allByRequestedPNRNumber.isEmpty()){
            throw new ResourceNotFoundException("ExchangePNR data is not present with requestedPNR number "+ requestedPNRNumber);
        }

        //ExchangePNR exchangePNRRecord = byRequestedPNR.get();


        Short requestedUserTrainNumber = requestedPNRRecord.getBookings().getTrainNumber();
        Short proposedUserTrainNumber = proposedPNRRecord.getBookings().getTrainNumber();

        if(! requestedUserTrainNumber.equals(proposedUserTrainNumber)){
            throw new ExchangeNotPossibleException(" Train number should be same to exchange the seats!!!");
        }

        LocalDate requestedUserDateOfJourney = requestedPNRRecord.getBookings().getDateOfJourney();
        LocalDate proposedUserDateOfJourney = proposedPNRRecord.getBookings().getDateOfJourney();

        if (! requestedUserDateOfJourney.equals(proposedUserDateOfJourney)){
            throw new ExchangeNotPossibleException("Date of Journey should be same to exchange the seats!!!");
        }

        String requestedUserBoardingPoint = requestedPNRRecord.getBookings().getBoardingPoint();
        String proposedUserBoardingPoint = proposedPNRRecord.getBookings().getBoardingPoint();

        if (! requestedUserBoardingPoint.equals(proposedUserBoardingPoint)){
            throw new ExchangeNotPossibleException("Boarding point should be same to exchange the seats!!!");
        }

        String requestedUserDestination = requestedPNRRecord.getBookings().getDestination();
        String proposedUserDestination = proposedPNRRecord.getBookings().getDestination();

        if(! requestedUserDestination.equals(proposedUserDestination)){
            throw new ExchangeNotPossibleException("Destination should be same to exchange the seats!!!");
        }

        ExchangePNR exchangePNRRecord = allByRequestedPNRNumber.get(0);

        if(exchangePNRRecord.getProposalPNRNumber()==null){
            if(! exchangePNRRecord.getExchangeStatus().equals("EXCHANGED")){
                exchangePNRRecord.setExchangeStatus("AGREED");
                exchangePNRRecord.setProposalPNRNumber(proposedPNRNumber);
            }
        }
        else{
            ExchangePNR exchangePNR = new ExchangePNR();
            exchangePNR.setRequestedPNRNumber(requestedPNRNumber);
            exchangePNR.setProposalPNRNumber(proposedPNRNumber);
            exchangePNR.setExchangeStatus("AGREED");
            return exchangePNRRepository.save(exchangePNR);
        }

        return exchangePNRRepository.save(exchangePNRRecord);
    }

    public ExchangePNR acceptAgreedProposal(Long requestedPNRNumber, Long proposedPNRNumber){

        PNRRecord requestedPNRRecord = pnrRecordService.getByPNRNumber(requestedPNRNumber);

        PNRRecord proposedPNRRecord = pnrRecordService.getByPNRNumber(proposedPNRNumber);

        Optional<ExchangePNR> byRequestedPNRAndProposalPNR = exchangePNRRepository.getByRequestedPNRNumberAndProposalPNRNumber(requestedPNRNumber, proposedPNRNumber);
        if(byRequestedPNRAndProposalPNR.isEmpty()){
            throw new ResourceNotFoundException("ExchangePNR record not found with RequestedPNR "+requestedPNRNumber+" and proposedPNR "+proposedPNRNumber);
        }
        ExchangePNR exchangePNR = byRequestedPNRAndProposalPNR.get();
        if(exchangePNR.getExchangeStatus().equals("AGREED")){
            exchangePNR.setExchangeStatus("ACCEPTED");
        }
        return exchangePNRRepository.save(exchangePNR);
    }

    @Transactional
    public ExchangePNR exchangePNR(Long requestedPNRNumber, Long proposedPNRNumber){

        PNRRecord requestedPNRRecord = pnrRecordService.getByPNRNumber(requestedPNRNumber);

        PNRRecord proposedPNRRecord = pnrRecordService.getByPNRNumber(proposedPNRNumber);

        Optional<ExchangePNR> byRequestedPNRAndProposalPNR = exchangePNRRepository.getByRequestedPNRNumberAndProposalPNRNumber(requestedPNRNumber, proposedPNRNumber);
        if(byRequestedPNRAndProposalPNR.isEmpty()){
            throw new ResourceNotFoundException("ExchangePNR record not found with RequestedPNR "+requestedPNRNumber+" and proposedPNR "+proposedPNRNumber);
        }
        ExchangePNR exchangePNR = byRequestedPNRAndProposalPNR.get();

        if(exchangePNR.getExchangeStatus().equals("CANCELLED")){
            throw new ExchangeNotPossibleException("Exchange status should be in ACCEPTED state");
        }
        else if(exchangePNR.getExchangeStatus().equals("ACCEPTED")){

            Short requestedUserTrainNumber = requestedPNRRecord.getBookings().getTrainNumber();
            Short proposedUserTrainNumber = proposedPNRRecord.getBookings().getTrainNumber();

            if(! requestedUserTrainNumber.equals(proposedUserTrainNumber)){
                throw new ExchangeNotPossibleException(" Train number should be same to exchange the seats!!!");
            }

            LocalDate requestedUserDateOfJourney = requestedPNRRecord.getBookings().getDateOfJourney();
            LocalDate proposedUserDateOfJourney = proposedPNRRecord.getBookings().getDateOfJourney();

            if (! requestedUserDateOfJourney.equals(proposedUserDateOfJourney)){
                throw new ExchangeNotPossibleException("Date of Journey should be same to exchange the seats!!!");
            }

            String requestedUserBoardingPoint = requestedPNRRecord.getBookings().getBoardingPoint();
            String proposedUserBoardingPoint = proposedPNRRecord.getBookings().getBoardingPoint();

            if (! requestedUserBoardingPoint.equals(proposedUserBoardingPoint)){
                throw new ExchangeNotPossibleException("Boarding point should be same to exchange the seats!!!");
            }

            String requestedUserDestination = requestedPNRRecord.getBookings().getDestination();
            String proposedUserDestination = proposedPNRRecord.getBookings().getDestination();

            if(! requestedUserDestination.equals(proposedUserDestination)){
                throw new ExchangeNotPossibleException("Destination should be same to exchange the seats!!!");
            }

//            PNRRecord tempPNRRecord = new PNRRecord();
//
//            tempPNRRecord.setPNRId(requestedPNRRecord.getPNRId());
//            tempPNRRecord.setPNRNumber(requestedPNRRecord.getPNRNumber());
//            tempPNRRecord.setUser(requestedPNRRecord.getUser());
//            tempPNRRecord.setBookings(requestedPNRRecord.getBookings());

            String tempTicketStatus = requestedPNRRecord.getBookings().getCurrentTicketStatus();

            requestedPNRRecord.getBookings().setCurrentTicketStatus(proposedPNRRecord.getBookings().getCurrentTicketStatus());
            proposedPNRRecord.getBookings().setCurrentTicketStatus(tempTicketStatus);

            pnrRecordRepository.save(requestedPNRRecord);
            pnrRecordRepository.save(proposedPNRRecord);


//            System.out.println("Temp before initiating "+tempPNRRecord.getBookings().getCurrentTicketStatus());
//            System.out.println("obj before:" + tempPNRRecord);
//
//            PNRRecord updatedRequestedUserPNRRecord = pnrRecordService.updatePNRRecordForExchange(requestedPNRRecord, proposedPNRRecord);
//            System.out.println("obj aft:" + tempPNRRecord);
//            System.out.println("Temp after changing requester seat "+tempPNRRecord.getBookings().getCurrentTicketStatus());
//            PNRRecord updatedProposedUserPNRRecord = pnrRecordService.updatePNRRecordForExchange(proposedPNRRecord, tempPNRRecord);
//            System.out.println("Temp after changing proposer seat "+tempPNRRecord.getBookings().getCurrentTicketStatus());
//
            List<ExchangePNR> allByRequestedPNRNumber = exchangePNRRepository.getAllByRequestedPNRNumber(requestedPNRNumber);
                    for(ExchangePNR exchangePNR1:  allByRequestedPNRNumber){
                        if (! exchangePNR1.getProposalPNRNumber().equals(proposedPNRNumber)){
                            exchangePNR1.setExchangeStatus("CANCELLED");
                        }
                    }
            List<ExchangePNR> rejectedExchangePNRS = exchangePNRRepository.saveAll(allByRequestedPNRNumber);

            ExchangedDetails exchangedDetails = new ExchangedDetails(requestedPNRRecord.getPNRId(),requestedPNRNumber,proposedPNRNumber);
            exchangedDetailsService.saveSuccessfullyExchangedDetails(exchangedDetails);

            exchangePNR.setExchangeStatus("EXCHANGED");
            return exchangePNRRepository.save(exchangePNR);
        }
        throw new ExchangeNotPossibleException("Exchange status is not in ACCEPTED status yet!!!");


    }

    public ExchangePNR cancelExchange(Long requestedPNRNumber, Long proposedPNRNumber){
        PNRRecord requestedPNRRecord = pnrRecordService.getByPNRNumber(requestedPNRNumber);

        PNRRecord proposedPNRRecord = pnrRecordService.getByPNRNumber(proposedPNRNumber);

        Optional<ExchangePNR> byRequestedPNRAndProposalPNR = exchangePNRRepository.getByRequestedPNRNumberAndProposalPNRNumber(requestedPNRNumber, proposedPNRNumber);
        if(byRequestedPNRAndProposalPNR.isEmpty()){
            throw new ResourceNotFoundException("ExchangePNR record not found with RequestedPNR "+requestedPNRNumber+" and proposedPNR "+proposedPNRNumber);
        }
        ExchangePNR exchangePNR = byRequestedPNRAndProposalPNR.get();


        if(exchangePNR.getExchangeStatus().equals("EXCHANGED")){
            throw new ExchangeNotPossibleException("Ticket is already exchanged succesfully!!! Can't cancel the EXCHANGED Ticket!!!");
        }
        else if(! exchangePNR.getExchangeStatus().equals("CANCELLED")){
            exchangePNR.setExchangeStatus("CANCELLED");
            return exchangePNRRepository.save(exchangePNR);
        }
        else {
            throw new ExchangeNotPossibleException("Exchange status in cancelled state!!!");
        }
    }

}
