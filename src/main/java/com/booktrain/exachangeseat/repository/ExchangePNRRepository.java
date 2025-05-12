package com.booktrain.exachangeseat.repository;

import com.booktrain.exachangeseat.entity.ExchangePNR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangePNRRepository extends JpaRepository<ExchangePNR,Long> {

    public Optional<ExchangePNR> getByRequestedPNRNumber(Long requestedPNRNumber);

    public List<ExchangePNR> getAllByRequestedPNRNumber(Long requestedPNRNumber);

    public Optional<ExchangePNR> getByRequestedPNRNumberAndProposalPNRNumber(Long requestedPNRNumber, Long proposedPNRNumber);
}
