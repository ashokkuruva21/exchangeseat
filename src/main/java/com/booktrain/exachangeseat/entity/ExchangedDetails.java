package com.booktrain.exachangeseat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExchangedDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long successfulExchangeId;

    private Long exchangePNRId;

    private Long requestedPNRNumber;

    private Long exchangedPNRNumber;


    public ExchangedDetails(Long successfulExchangeId) {
        this.successfulExchangeId = successfulExchangeId;
    }

    public ExchangedDetails(Long successfulExchangeId, Long exchangePNRId, Long requestedPNRNumber, Long exchangedPNRNumber) {
        this.successfulExchangeId = successfulExchangeId;
        this.exchangePNRId = exchangePNRId;
        this.requestedPNRNumber = requestedPNRNumber;
        this.exchangedPNRNumber = exchangedPNRNumber;
    }

    public ExchangedDetails(Long exchangePNRId, Long requestedPNRNumber, Long exchangedPNRNumber) {
        this.exchangePNRId = exchangePNRId;
        this.requestedPNRNumber = requestedPNRNumber;
        this.exchangedPNRNumber = exchangedPNRNumber;
    }

    public Long getSuccessfulExchangeId() {
        return successfulExchangeId;
    }

    public void setSuccessfulExchangeId(Long successfulExchangeId) {
        this.successfulExchangeId = successfulExchangeId;
    }

    public Long getExchangePNRId() {
        return exchangePNRId;
    }

    public void setExchangePNRId(Long exchangePNRId) {
        this.exchangePNRId = exchangePNRId;
    }

    public Long getRequestedPNRNumber() {
        return requestedPNRNumber;
    }

    public void setRequestedPNRNumber(Long requestedPNRNumber) {
        this.requestedPNRNumber = requestedPNRNumber;
    }

    public Long getExchangedPNRNumber() {
        return exchangedPNRNumber;
    }

    public void setExchangedPNRNumber(Long exchangedPNRNumber) {
        this.exchangedPNRNumber = exchangedPNRNumber;
    }
}
