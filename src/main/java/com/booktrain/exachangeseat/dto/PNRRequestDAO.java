package com.booktrain.exachangeseat.dto;

import java.time.LocalDate;

public class PNRRequestDAO {

    private Long requestedPNR;

    private Short trainNumber;

    private LocalDate dateOfJourney;

    private String classStatus;

    private String currentTicketStatus;

    private String boardingPoint;

    private String destination;

    private String currentExchangeStatus;

    public PNRRequestDAO() {
    }

    public PNRRequestDAO(Long requestedPNR, Short trainNumber, LocalDate dateOfJourney, String classStatus, String currentTicketStatus, String boardingPoint, String destination, String currentExchangeStatus) {

        this.requestedPNR = requestedPNR;
        this.trainNumber = trainNumber;
        this.dateOfJourney = dateOfJourney;
        this.classStatus = classStatus;
        this.currentTicketStatus = currentTicketStatus;
        this.boardingPoint = boardingPoint;
        this.destination = destination;
        this.currentExchangeStatus = currentExchangeStatus;
    }

    public Long getRequestedPNR() {
        return requestedPNR;
    }

    public void setRequestedPNR(Long requestedPNR) {
        this.requestedPNR = requestedPNR;
    }

    public Short getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(Short trainNumber) {
        this.trainNumber = trainNumber;
    }

    public LocalDate getDateOfJourney() {
        return dateOfJourney;
    }

    public void setDateOfJourney(LocalDate dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCurrentExchangeStatus() {
        return currentExchangeStatus;
    }

    public void setCurrentExchangeStatus(String currentExchangeStatus) {
        this.currentExchangeStatus = currentExchangeStatus;
    }

    public String getCurrentTicketStatus() {
        return currentTicketStatus;
    }

    public void setCurrentTicketStatus(String currentTicketStatus) {
        this.currentTicketStatus = currentTicketStatus;
    }
}
