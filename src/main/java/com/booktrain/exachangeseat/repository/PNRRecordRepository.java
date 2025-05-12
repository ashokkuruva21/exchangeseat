package com.booktrain.exachangeseat.repository;

import com.booktrain.exachangeseat.entity.PNRRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PNRRecordRepository extends JpaRepository<PNRRecord,Long> {

    public Optional<PNRRecord> getByPNRNumber(Long pnrNumber);
}
