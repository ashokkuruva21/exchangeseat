package com.booktrain.exachangeseat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Short trainNumber;

    private LocalDate dateOfJourney;

    private String classStatus;

    private String boardingPoint;

    private String destination;

    private String bookingStatus;

    private String currentTicketStatus;

    private String bookingChannel;

    private Double ticketPrice;

    public Bookings() {
    }

    public Bookings(Long bookingId, Short trainNumber, LocalDate dateOfJourney, String classStatus, String boardingPoint, String destination, String bookingStatus, String currentTicketStatus, String bookingChannel, Double ticketPrice) {
        this.bookingId = bookingId;
        this.trainNumber = trainNumber;
        this.dateOfJourney = dateOfJourney;
        this.classStatus = classStatus;
        this.boardingPoint = boardingPoint;
        this.destination = destination;
        this.bookingStatus = bookingStatus;
        this.currentTicketStatus = currentTicketStatus;
        this.bookingChannel = bookingChannel;
        this.ticketPrice = ticketPrice;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getCurrentTicketStatus() {
        return currentTicketStatus;
    }

    public void setCurrentTicketStatus(String currentTicketStatus) {
        this.currentTicketStatus = currentTicketStatus;
    }

    public String getBookingChannel() {
        return bookingChannel;
    }

    public void setBookingChannel(String bookingChannel) {
        this.bookingChannel = bookingChannel;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
