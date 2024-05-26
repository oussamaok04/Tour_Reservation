package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.Services.RoleService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    TourService service;

    @Autowired
    public TourController(TourService service){
        this.service = service;
    }

    //Retourne la list des tours
    @GetMapping("/all")
    public String allTours(Model model){
        List<Tour> tours = service.getAllTours();
        model.addAttribute("tours", tours);
        return "index";
    }

    //Retourne une page qui contient les informations d'une tour
    @GetMapping("/detail")
    public String getTourDetails(Model model, @RequestParam("id") Long id){
        try {
            Tour t = service.getTourById(id);
            model.addAttribute("tour", t);
        } catch (Exception e) {
            System.out.println("Tour was not found");
        }
        return "tourDetails";
    }

    @GetMapping("/save")
    public String addTour(){
        return "addTour";
    }

    //Todo : Tester save w edit
    @PostMapping("/save")
    public String addTour(@ModelAttribute("t") Tour tour, Model model){
        Tour tourToSave = new Tour();
        tourToSave.setId_tour(null);
        tourToSave.setTitre(tour.getTitre());
        tourToSave.setDate(tour.getDate());
        tourToSave.setPrix(tour.getPrix());
        tourToSave.setNombre_place(tour.getNombre_place());
        tourToSave.setDescription(tour.getDescription());
        tourToSave.setReservations(new ArrayList<>());
        service.addTour(tourToSave);
        return "redirect:/tours/all";
    }
    //Todo : Tester save w edit
    @GetMapping("/delete")
    public String deleteTour(@RequestParam("id") Long id){
        service.deleteTour(id);
        return "redirect:/tours/all";
    }
    //Todo : Tester save w edit
    @GetMapping("/edit")
    public String editTour(Model model, @RequestParam("id") Long id){
        try {
            Tour tour = service.getTourById(id);
            model.addAttribute("t", tour);
        } catch (Exception e) {
            System.out.println("Tour not found");
        }
        return "editTour";
    }
    //Todo : Tester save w edit
    @PostMapping("/edit")
    public String editTour(@ModelAttribute("t") Tour tour, Model model){
        service.addTour(tour);
        return "editTour";
    }
}
