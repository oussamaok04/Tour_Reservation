package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.Services.RoleService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TourController {

    TourService service;

    @Autowired
    public TourController(TourService service){
        this.service = service;
    }

    //Retourne la list des tours
    @GetMapping("/index")
    public String allTours(Model model){
        List<Tour> tours = service.getAllTours();
        model.addAttribute("tours", tours);
        return "index";
    }

    //Retourne une page qui contient les informations d'une tour
    @GetMapping("/tours")
    public String getTourDetails(Model model, @RequestParam("id") Long id){
        try {
            Tour t = service.getTourById(id);
            model.addAttribute("tour", t);
        } catch (Exception e) {
            System.out.println("Tour was not found");
        }
        return "tourDetails";
    }
}
