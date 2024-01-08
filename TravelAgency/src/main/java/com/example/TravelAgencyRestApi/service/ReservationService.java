package com.example.TravelAgencyRestApi.service;

import com.example.TravelAgencyRestApi.model.dtos.requests.CreateReservationDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateReservationDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseReservationDTO;

import java.util.List;

public interface ReservationService {
    ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO);
    List<ResponseReservationDTO>  getAllReservations();
    ResponseReservationDTO getReservationById(Long reservationId);
    ResponseReservationDTO updateReservation(UpdateReservationDTO update);
    Boolean deleteReservation(Long reservationId);

}
