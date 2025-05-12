package com.booktrain.exachangeseat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class ExchangePNR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangedId;

    private Long requestedPNRNumber;

    private Long proposalPNRNumber;

    //REQUESTED, AGREED, ACCEPTED, EXCHANGED, CANCELLED
    @Pattern(regexp = "^(REQUESTED|AGREED|ACCEPTED|EXCHANGED|CANCELLED)$")
    private String exchangeStatus;

    public ExchangePNR() {
    }

    public ExchangePNR(Long exchangedId, Long requestedPNRNumber, Long proposalPNRNumber, String exchangeStatus) {
        this.exchangedId = exchangedId;
        this.requestedPNRNumber = requestedPNRNumber;
        this.proposalPNRNumber = proposalPNRNumber;
        this.exchangeStatus = exchangeStatus;
    }

    public Long getExchangedId() {
        return exchangedId;
    }

    public void setExchangedId(Long exchangedId) {
        this.exchangedId = exchangedId;
    }

    public Long getRequestedPNRNumber() {
        return requestedPNRNumber;
    }

    public void setRequestedPNRNumber(Long requestedPNRNumber) {
        this.requestedPNRNumber = requestedPNRNumber;
    }

    public Long getProposalPNRNumber() {
        return proposalPNRNumber;
    }

    public void setProposalPNRNumber(Long proposalPNRNumber) {
        this.proposalPNRNumber = proposalPNRNumber;
    }


    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}
