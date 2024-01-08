package com.example.TravelAgencyRestApi.db;

import com.example.TravelAgencyRestApi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location,Long> {

}
