package com.example.reservationtours.DAO.Repositories;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTour(Tour tour);
    List<Reservation> findByUser(User user);
}

