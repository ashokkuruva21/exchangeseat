package com.booktrain.exachangeseat.service;


import com.booktrain.exachangeseat.entity.ExchangedDetails;
import com.booktrain.exachangeseat.exception.ExchangeNotPossibleException;
import com.booktrain.exachangeseat.repository.ExchangedDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangedDetailsService {

    @Autowired
    private ExchangedDetailsRepository exchangedDetailsRepository;


    public ExchangedDetails saveSuccessfullyExchangedDetails(ExchangedDetails exchangedDetails){

        Optional<ExchangedDetails> byExchangePNRId = exchangedDetailsRepository.getByExchangePNRId(exchangedDetails.getExchangePNRId());
        if(byExchangePNRId.isEmpty()){
            return exchangedDetailsRepository.save(exchangedDetails);
        }
        else {
            throw new ExchangeNotPossibleException("Exchanged details are already present!!!");
        }

    }
}
