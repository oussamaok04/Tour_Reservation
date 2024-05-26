package com.example.reservationtours.Web;

import com.example.reservationtours.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    ReservationService service;

    @Autowired
    public ReservationController(ReservationService service){
        this.service = service;
    }

    //demain nkhdmoha hh
}
