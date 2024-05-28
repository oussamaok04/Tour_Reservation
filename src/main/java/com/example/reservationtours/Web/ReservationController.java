package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.ReservationService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class ReservationController {


    private final ReservationService reservationService;
    private final TourService tourService;

    @Autowired
    public ReservationController(ReservationService reservationService, TourService tourService) {
        this.reservationService = reservationService;
        this.tourService = tourService;
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
    public String makeReservation(@ModelAttribute("reservation") Reservation reservation,Model model) throws Exception {

        Tour tour = reservation.getTour();
        System.out.println(reservation.toString());
        if (tour.getNombre_place() > 0) {
            // Diminuez le nombre de places disponibles
            tour.setNombre_place(tour.getNombre_place() - 1);

            // Ajoutez la réservation à la liste des réservations du tour
            tour.getReservations().add(reservation);

            // Mettez à jour la disponibilité du tour
            if (tour.getNombre_place() == 0) {
                tour.setDisponible(false);
            }

            // Sauvegardez les changements dans le tour
            tourService.editTour(tour.getId_tour(), tour);

            // Sauvegardez la réservation
            reservationService.addReservation(reservation);

            return "redirect:/tours/all";
        } else {
            // Gérer le cas où il n'y a plus de places disponibles (par exemple, afficher un message d'erreur)
            return "no-availability";
        }
    }

    private User getCurrentUser() {
        User user = new User();
        user.setId_user(1L); // Assurez-vous que cet ID existe dans votre base de données
        user.setUsername("defaultUser");
        return user;
    }
}
