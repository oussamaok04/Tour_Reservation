package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.Services.ReservationService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tours")
public class TourController {

    TourService service;
    ReservationService reservationService;
    @Autowired
    public TourController(TourService service, ReservationService reservationService){
        this.service = service;
        this.reservationService=reservationService;
    }

    //Retourne la list des tours
    @GetMapping({"/", "", "/index"})
    public String home(Model model){
        return "redirect:/tours/all";
    }


//    @GetMapping("/all")
//    public String allTours(Model model,
//                           @RequestParam(name = "page", defaultValue = "0") int page,
//                           @RequestParam(name = "size", defaultValue = "6") int size,
//                           @RequestParam(name = "search", defaultValue = "") String keyword) {
//        Page<Tour> tours = service.findByTitreContaining(keyword, PageRequest.of(page, size));
//        model.addAttribute("tours", tours);
//
//        // Pass the keyword for pagination links
//        model.addAttribute("keyword", keyword);
//
//        return "index";
//    }
@GetMapping("/all")
public String allTours(Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "6") int size,
                       @RequestParam(name = "search", defaultValue = "") String keyword) {
    Page<Tour> tours = service.findByTitreContaining(keyword, PageRequest.of(page, size));
    int[] pages = new int[tours.getTotalPages()];
    for (int i = 0; i < pages.length; i++) pages[i] = i;
    model.addAttribute("pages", pages);
    model.addAttribute("tours", tours.getContent());
    model.addAttribute("currentPage", page); // Ajout de l'attribut currentPage
    model.addAttribute("keyword", keyword); // Ajout de l'attribut keyword
    return "index";
}


    //Retourne une page qui contient les informations d'une tour
    @GetMapping("/details")
    public String getTourDetails(Model model, @RequestParam("id") Long id){
        try {
            Tour t = service.getTourById(id);
            model.addAttribute("tour", t);
           // model.addAttribute("tourId", id);
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
        tourToSave.setDuree(tour.getDuree());
        tourToSave.setNombre_place(tour.getNombre_place());
        tourToSave.setDescription(tour.getDescription());
        tourToSave.setDisponible(true);
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
            model.addAttribute("tourId", id);
        } catch (Exception e) {
            System.out.println("Tour not found");
        }
        return "editTour";
    }
    //Todo : Tester save w edit
    @PostMapping("/edit")
    public String editTour(@ModelAttribute("t") Tour tour, Model model) {
        service.addTour(tour);
        return "redirect:/tours/details?id="+tour.getId_tour();
    }
    // Nouveau mapping pour afficher les réservations d'un tour
    @GetMapping("/reservations")
    public String getReservationsForTour(@RequestParam("id") Long id, Model model) {
        try {
            Tour tour = service.getTourById(id);
            List<Reservation> reservations = reservationService.getReservationByTour(tour);
            model.addAttribute("tour", tour);
            model.addAttribute("reservations", reservations);
        } catch (Exception e) {
            System.out.println("Tour not found");
        }
        return "tourReservations";
    }


}
