//package com.example.reservationtours.Services.Implementations;
//
//import com.example.reservationtours.DAO.Entities.Reservation;
//import com.example.reservationtours.DAO.Entities.Tour;
//import com.example.reservationtours.DAO.Entities.User;
//import com.example.reservationtours.DAO.Repositories.ReservationRepository;
//import com.example.reservationtours.DAO.Repositories.TourRepository;
//import com.example.reservationtours.Services.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//@Transactional
//@Service
//public class ReservationServiceImpl implements ReservationService {
//
//    ReservationRepository reservationRepository;
//
//    @Autowired
//    public ReservationServiceImpl(ReservationRepository repo){
//        this.reservationRepository = repo;
//    }
//
//    //************************* implementations *****************
//
//    @Override
//    public List<Reservation> getAllReservations() {
//        return reservationRepository.findAll();
//    }
//
//    @Override
//    public Reservation getReservationById(Long id) throws Exception {
//        Optional<Reservation> res = reservationRepository.findById(id);
//        if (res.isPresent()){
//            return res.get();
//        } else {
//            throw new Exception("Reservaation was not found : FindResById");
//        }
//    }
//
//    @Override
//    public Reservation addReservation(Reservation reservation) {
//        return reservationRepository.save(reservation);
//    }
//
//    @Override
//    public Reservation editReservation(Long id, Reservation reservation) throws Exception {
//        Optional<Reservation> resToEdit = reservationRepository.findById(id);
//        Reservation resFromDb;
//
//        if (resToEdit.isPresent()){
//            resFromDb = resToEdit.get();
//            resFromDb.setDate_reservation(reservation.getDate_reservation());
//            resFromDb.setTour(reservation.getTour());
//            resFromDb.setUser(reservation.getUser());
//        } else {
//            throw new Exception("Reservation was not found : EditRes");
//        }
//
//        return reservationRepository.save(resFromDb);
//    }
//
//    @Override
//    public List<Reservation> getReservationByTour(Tour tour) {
//        return reservationRepository.findByTour(tour);
//    }
//
//    @Override
//    public void deleteReservation(Long id) {
//        reservationRepository.deleteById(id);
//    }
//}
package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.DAO.Repositories.ReservationRepository;
import com.example.reservationtours.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) throws Exception {
        Optional<Reservation> res = reservationRepository.findById(id);
        if (res.isPresent()) {
            return res.get();
        } else {
            throw new Exception("Reservation was not found : FindResById");
        }
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(Long id, Reservation reservation) throws Exception {
        Optional<Reservation> resToEdit = reservationRepository.findById(id);
        if (resToEdit.isPresent()) {
            Reservation resFromDb = resToEdit.get();
            resFromDb.setDate_reservation(reservation.getDate_reservation());
            resFromDb.setTour(reservation.getTour());
            resFromDb.setUser(reservation.getUser());
            return reservationRepository.save(resFromDb);
        } else {
            throw new Exception("Reservation was not found : EditRes");
        }
    }

    @Override
    public List<Reservation> getReservationByTour(Tour tour) {
        return reservationRepository.findByTour(tour);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getReservationByUser(User user) {
        return reservationRepository.findByUser(user);
    }
}
