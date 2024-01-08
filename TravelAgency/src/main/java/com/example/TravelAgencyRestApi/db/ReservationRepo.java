package com.example.TravelAgencyRestApi.db;

import com.example.TravelAgencyRestApi.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation,Long> {
}
