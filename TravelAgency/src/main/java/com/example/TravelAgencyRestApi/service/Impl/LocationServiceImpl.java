package com.example.TravelAgencyRestApi.service.Impl;

import com.example.TravelAgencyRestApi.db.LocationRepo;
import com.example.TravelAgencyRestApi.model.dtos.requests.CreateLocationDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseLocationDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateLocationDTO;
import com.example.TravelAgencyRestApi.model.Location;
import com.example.TravelAgencyRestApi.service.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepo locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepo locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = new Location();
        BeanUtils.copyProperties(createLocationDTO, location);

        Location savedLocation = locationRepository.save(location);

        ResponseLocationDTO responseLocationDTO = new ResponseLocationDTO();
        BeanUtils.copyProperties(savedLocation, responseLocationDTO);

        return responseLocationDTO;
    }
    public Boolean deleteLocation(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
            return true;
        }
        return false;
    }
    public List<ResponseLocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private ResponseLocationDTO converter(Location location) {
        ResponseLocationDTO responseLocationDTO = new ResponseLocationDTO();
        BeanUtils.copyProperties(location, responseLocationDTO);
        return responseLocationDTO;
    }
    public ResponseLocationDTO getLocationById(Long locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        return locationOptional.map(this::converter).orElse(null);
    }
    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Optional<Location> locationOptional = locationRepository.findById(updateLocationDTO.getId());
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            BeanUtils.copyProperties(updateLocationDTO, location);
            Location updatedLocation = locationRepository.save(location);
            return converter(updatedLocation);
        }
        return null;
    }

}
