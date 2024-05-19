package com.example.reservationtours.Services;

import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();
    Tour getTourById(Long id) throws Exception;
    List<Tour> getTourByName(String name);
    Tour addTour(Tour tour);
    Tour editTour(Long id, Tour tour) throws Exception;
    void deleteTour(Long id);

}
