package com.booktrain.exachangeseat.repository;

import com.booktrain.exachangeseat.entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Long> {
}
