package com.example.reservationtours.DAO.Repositories;

import com.example.reservationtours.DAO.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
