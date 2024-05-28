package com.example.reservationtours.Services;

import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();
    Tour getTourById(Long id) throws Exception;
    List<Tour> getTourByName(String name);
    Tour addTour(Tour tour);
    Tour editTour(Long id, Tour tour) throws Exception;
    void deleteTour(Long id);

    Page<Tour> findByTitreContaining(String fullname, Pageable pageable);


}
