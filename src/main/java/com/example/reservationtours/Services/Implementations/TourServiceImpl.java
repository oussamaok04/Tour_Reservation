package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Repositories.RoleRepository;
import com.example.reservationtours.DAO.Repositories.TourRepository;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository repo){
        this.tourRepository = repo;
    }

    //************************* implementations *****************

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
//    public Tour getTourById(Long id) throws Exception {
//        Optional<Tour> tour = tourRepository.findById(id);
//        if (tour.isPresent()){
//            return tour.get();
//        } else {
//            throw new Exception("Reservaation was not found : FindResById");
//        }
//    }
    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tour> getTourByName(String name) {
        return tourRepository.findToursByTitre(name);
    }

    @Override
    public Tour addTour(Tour tour) {
        return tourRepository.save(tour);
    }

    //Fixed a typo problem in editTour
    @Override
    public Tour editTour(Long id, Tour tour) throws Exception {
        Optional<Tour> tourToEdit = tourRepository.findById(id);
        Tour tourFromDb;

        if (tourToEdit.isPresent()){
            tourFromDb = tourToEdit.get();
            tourFromDb.setTitre(tour.getTitre());
            tourFromDb.setDescription(tour.getDescription());
            tourFromDb.setDate(tour.getDate());
            tourFromDb.setDuree(tour.getDuree());
            tourFromDb.setDisponible(tour.isDisponible());
            tourFromDb.setEvaluation(tour.getEvaluation());
            tourFromDb.setPrix(tour.getPrix());
            tourFromDb.setNombre_place(tour.getNombre_place());
            tourFromDb.setReservations(tour.getReservations());
        } else {
            throw new Exception("Tour was not found : EditRes");
        }

        return tourRepository.save(tourFromDb);
    }

    //Added deleteTour method
    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}
