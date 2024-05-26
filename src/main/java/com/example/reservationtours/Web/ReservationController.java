package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.Services.ReservationService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    ReservationService reservationService;
    TourService tourService;

    @Autowired
    public ReservationController(ReservationService reservationService, TourService tourService) {
        this.reservationService = reservationService;
        this.tourService = tourService;
    }
    @GetMapping("/reservations/new/{tourId}")
    public String showReservationForm(@PathVariable Long tourId, Model model) throws Exception {
        Reservation reservation = new Reservation();
        reservation.setTour(tourService.getTourById(tourId));
        model.addAttribute("reservation", reservation);
        model.addAttribute("tour",reservation.getTour());
        model.addAttribute("user",reservation.getUser());
        return "redirect:/reservation_form.html";
    }

    @PostMapping("/reservations")
    public String makeReservation(Reservation reservation) {
        reservationService.addReservation(reservation);
        return "redirect:/tours/all";
    }


}
