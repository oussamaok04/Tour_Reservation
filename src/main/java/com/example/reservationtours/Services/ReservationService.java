package com.example.reservationtours.Services;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id) throws Exception;
    Reservation addReservation(Reservation reservation);
    Reservation editReservation(Long id, Reservation reservation) throws Exception;
    List<Reservation> getReservationByTour(Tour tour);
    void deleteReservation(Long id);
}
