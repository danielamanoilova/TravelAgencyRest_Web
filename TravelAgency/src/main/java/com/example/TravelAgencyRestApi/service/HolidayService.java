package com.example.TravelAgencyRestApi.service;

import com.example.TravelAgencyRestApi.model.dtos.requests.CreateHolidayDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateHolidayDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseHolidayDTO;

import java.util.List;

public interface HolidayService {
    ResponseHolidayDTO createHoliday(CreateHolidayDTO createHolidayDTO);
    List<ResponseHolidayDTO> getAllHolidays();
    ResponseHolidayDTO getHolidayById(Long holidayId);
    ResponseHolidayDTO updateHoliday(UpdateHolidayDTO update);
    Boolean deleteHoliday(Long holidayId);

}
