package com.example.TravelAgencyRestApi.service.Impl;

import com.example.TravelAgencyRestApi.db.HolidayRepo;
import com.example.TravelAgencyRestApi.db.LocationRepo;
import com.example.TravelAgencyRestApi.model.dtos.requests.CreateHolidayDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateHolidayDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseHolidayDTO;
import com.example.TravelAgencyRestApi.model.Holiday;
import com.example.TravelAgencyRestApi.model.Location;
import com.example.TravelAgencyRestApi.service.HolidayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayServiceImpl implements HolidayService {
        private final HolidayRepo repository;
        private final LocationRepo locationRepository;
        @Autowired
        public HolidayServiceImpl(HolidayRepo repository, LocationRepo locationRepository) {
        this.repository = repository;
        this.locationRepository = locationRepository;
        }


        private ResponseHolidayDTO converterer(Holiday holiday) {
            ResponseHolidayDTO response = new ResponseHolidayDTO();
            BeanUtils.copyProperties(holiday, response);
            return response;
        }
        private Location loadLocationById(long id){
        return locationRepository.findById(id).get();
        }

        @Override
        public ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO) {

            Holiday holiday = new Holiday();

            Location location = this.loadLocationById(createHolidayDTO.getLocation());

            holiday.setLocation(location);
            BeanUtils.copyProperties(createHolidayDTO, holiday);
            Holiday savedLocation = repository.save(holiday);
            ResponseHolidayDTO response = new ResponseHolidayDTO();
            BeanUtils.copyProperties(savedLocation, response);
            return response;
        }

    @Override
    public List<ResponseHolidayDTO> getAllHolidays() {
        List<Holiday> responce = repository.findAll();

        return responce.stream().map(this::converterer).collect(Collectors.toList());
    }

    @Override
    public ResponseHolidayDTO getHolidayById(Long holidayId) {
        Optional<Holiday> optionalHoliday = repository.findById(holidayId);
        return optionalHoliday.map(this::converterer).orElse(null);
    }

    @Override
    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO update) {
        Optional<Holiday> holidayOptional = repository.findById(update.getId());
        if (holidayOptional.isPresent()) {
            Holiday holiday = holidayOptional.get();
            BeanUtils.copyProperties(update, holiday);
            Holiday updatedHoliday = repository.save(holiday);
            return converterer(updatedHoliday);
        }
        return null;
    }

    @Override
    public Boolean deleteHoliday(Long holidayId) {
        if (repository.existsById(holidayId)) {
            repository.deleteById(holidayId);
            return true;
        }
        return false;
    }


}