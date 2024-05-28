package com.example.reservationtours.DAO.Repositories;

import com.example.reservationtours.DAO.Entities.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findToursByTitre(String titre);
    Page<Tour> findByTitreContaining(String fullname, Pageable pageable);
}
