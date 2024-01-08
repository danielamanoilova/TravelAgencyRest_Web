package com.example.TravelAgencyRestApi.db;

import com.example.TravelAgencyRestApi.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepo extends JpaRepository<Holiday,Long> {
}
