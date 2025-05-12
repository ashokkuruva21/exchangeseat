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

    private Long requestedPNR;

    private Long proposalPNR;

    //REQUESTED, AGREED, ACCEPTED, EXCHANGED, CANCELLED
    @Pattern(regexp = "^(REQUESTED|AGREED|ACCEPTED|EXCHANGED|CANCELLED)$")
    private String exchangeStatus;

    public ExchangePNR() {
    }

    public ExchangePNR(Long exchangedId, Long requestedPNR, Long proposalPNR, String exchangeStatus) {
        this.exchangedId = exchangedId;
        this.requestedPNR = requestedPNR;
        this.proposalPNR = proposalPNR;
        this.exchangeStatus = exchangeStatus;
    }

    public Long getExchangedId() {
        return exchangedId;
    }

    public void setExchangedId(Long exchangedId) {
        this.exchangedId = exchangedId;
    }

    public Long getRequestedPNR() {
        return requestedPNR;
    }

    public void setRequestedPNR(Long requestedPNR) {
        this.requestedPNR = requestedPNR;
    }

    public Long getProposalPNR() {
        return proposalPNR;
    }

    public void setProposalPNR(Long proposalPNR) {
        this.proposalPNR = proposalPNR;
    }


    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}
