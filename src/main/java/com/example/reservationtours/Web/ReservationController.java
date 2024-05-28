package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.ReservationService;
import com.example.reservationtours.Services.TourService;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final TourService tourService;
    private final UserService userService;
    @Autowired
    public ReservationController(ReservationService reservationService, TourService tourService, UserService userService) {
        this.reservationService = reservationService;
        this.tourService = tourService;
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/reservations/new/{tourId}")
    public String showReservationForm(@PathVariable Long tourId, Model model) throws Exception {
        Reservation reservation = new Reservation();
        Tour tour = tourService.getTourById(tourId);
        reservation.setTour(tour);
        User user = getCurrentUser(); // Implémentez cette méthode pour obtenir l'utilisateur connecté
        reservation.setUser(user);
        reservation.setDate_reservation(new Date());

        // Formater la date en chaîne de caractères
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(reservation.getDate_reservation());

        model.addAttribute("reservation", reservation);
        model.addAttribute("formattedDate", formattedDate);
        return "reservation_form";
    }

    @Transactional
    @PostMapping("/reservations")
    public String makeReservation(@ModelAttribute("reservation") Reservation reservation, Model model) throws Exception {
        System.out.println(reservation);
        Tour tour = tourService.getTourById(reservation.getTour().getId_tour());

        if (tour.getNombre_place() > 0) {
            // Diminuez le nombre de places disponibles
            tour.setNombre_place(tour.getNombre_place() - 1);

            // Ajoutez la réservation à la liste des réservations du tour
            reservation.setTour(tour);
            // Sauvegardez la réservation
            reservationService.addReservation(reservation);
            // Ajoutez cette ligne pour s'assurer que la réservation est liée au tour
            tour.getReservations().add(reservation);

            // Mettez à jour la disponibilité du tour
            if (tour.getNombre_place() == 0) {
                tour.setDisponible(false);
            }

            // Sauvegardez les changements dans le tour
            tourService.editTour(tour.getId_tour(), tour);

            // Sauvegardez la réservation
            // reservationService.addReservation(reservation);

            return "redirect:/tours/all";
        } else {
            // Gérer le cas où il n'y a plus de places disponibles (par exemple, afficher un message d'erreur)
            return "no-availability";
        }
    }
    @GetMapping("/reservations/all")
    public String allReservation(Model model){
        model.addAttribute("reservations",reservationService.getAllReservations());
        return "listReservation";
    }
    @GetMapping("/reservations/user")
    public String userReservation(Model model){
        User user=getCurrentUser();
        model.addAttribute("reservations",reservationService.getReservationByUser(user));
        return "listReservation";
    }

private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
        String username = authentication.getName();
        // Vous pouvez utiliser ce nom d'utilisateur pour récupérer les détails de l'utilisateur depuis votre service UserService
        // Par exemple: User user = userService.findByUsername(username);
        User user = userService.findByUsername(username);
        return user;
    }
    return null;
}
}
