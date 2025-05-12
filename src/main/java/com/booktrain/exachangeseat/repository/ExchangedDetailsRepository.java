package com.booktrain.exachangeseat.repository;

import com.booktrain.exachangeseat.entity.ExchangedDetails;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangedDetailsRepository extends JpaRepository<ExchangedDetails,Long> {

    public Optional<ExchangedDetails> getByExchangePNRId(Long exchangePNRId);
}
