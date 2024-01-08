package com.example.TravelAgencyRestApi.service;

import com.example.TravelAgencyRestApi.model.dtos.requests.CreateLocationDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseLocationDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateLocationDTO;

import java.util.List;

public interface LocationService {
    ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO);
    List<ResponseLocationDTO> getAllLocations();
    ResponseLocationDTO getLocationById(Long locationId);

    ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO);
    Boolean deleteLocation(Long locationId);
}
