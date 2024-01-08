package com.example.TravelAgencyRestApi.service.Impl;

import com.example.TravelAgencyRestApi.db.HolidayRepo;
import com.example.TravelAgencyRestApi.db.ReservationRepo;
import com.example.TravelAgencyRestApi.model.Holiday;
import com.example.TravelAgencyRestApi.model.Reservation;
import com.example.TravelAgencyRestApi.model.dtos.requests.CreateReservationDTO;
import com.example.TravelAgencyRestApi.model.dtos.requests.UpdateReservationDTO;
import com.example.TravelAgencyRestApi.model.dtos.responses.ResponseReservationDTO;
import com.example.TravelAgencyRestApi.service.ReservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final HolidayRepo holidayRepository;
    private final ReservationRepo reservationRepository;

    @Autowired
    public ReservationServiceImpl(HolidayRepo holidayRepository, ReservationRepo reservationRepository) {
        this.holidayRepository = holidayRepository;
        this.reservationRepository = reservationRepository;
    }
    private Holiday loadReservByID(long id){
        return holidayRepository.findById(id).get();
    }


    @Override
    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {

        Reservation reservation = new Reservation();

        Holiday holiday = this.loadReservByID(createReservationDTO.getHoliday());
        reservation.setHoliday(holiday);
        BeanUtils.copyProperties(createReservationDTO,reservation);

        Reservation savedReservation = reservationRepository.save(reservation);
        ResponseReservationDTO response = new ResponseReservationDTO();
        BeanUtils.copyProperties(savedReservation,response);
        return response;
    }


    @Override
    public List<ResponseReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseReservationDTO getReservationById(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        return reservation.map(this::converter).orElse(null);
    }

    @Override
    public ResponseReservationDTO updateReservation(UpdateReservationDTO update) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(update.getId());
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            BeanUtils.copyProperties(update, reservation);
            reservation.setHoliday(holidayRepository.findById(update.getHoliday()).orElse(null));

            Reservation updatedReservation = reservationRepository.save(reservation);
            return converter(updatedReservation);
        }
        return null;
    }
    private ResponseReservationDTO converter(Reservation reservation) {
        ResponseReservationDTO response = new ResponseReservationDTO();
        BeanUtils.copyProperties(reservation, response);
        return response;
    }

    @Override
    public Boolean deleteReservation(Long reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
            return true;
        }
        return false;
    }




}

