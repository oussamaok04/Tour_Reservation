package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.DAO.Repositories.ReservationRepository;
import com.example.reservationtours.DAO.Repositories.TourRepository;
import com.example.reservationtours.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repo){
        this.reservationRepository = repo;
    }

    //************************* implementations *****************

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws Exception {
        Optional<Reservation> res = reservationRepository.findById(id);
        if (res.isPresent()){
            return res.get();
        } else {
            throw new Exception("Reservaation was not found : FindResById");
        }
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(Long id, Reservation reservation) throws Exception {
        Optional<Reservation> resToEdit = reservationRepository.findById(id);
        Reservation resFromDb;

        if (resToEdit.isPresent()){
            resFromDb = resToEdit.get();
            resFromDb.setDate_reservation(reservation.getDate_reservation());
            resFromDb.setTour(reservation.getTour());
            resFromDb.setUser(reservation.getUser());
        } else {
            throw new Exception("Reservation was not found : EditRes");
        }

        return reservationRepository.save(resFromDb);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
