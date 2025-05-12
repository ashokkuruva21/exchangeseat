package com.booktrain.exachangeseat.repository;

import com.booktrain.exachangeseat.entity.ExchangePNR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangePNRRepository extends JpaRepository<ExchangePNR,Long> {

    public Optional<ExchangePNR> getByRequestedPNR(Long requestedPNR);

    public Optional<ExchangePNR> getByRequestedPNRAndProposalPNR(Long requestedPNRNumber, Long proposedPNRNumber);
}
