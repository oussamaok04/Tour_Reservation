package com.example.reservationtours.Web;

import com.example.reservationtours.Services.RoleService;
import com.example.reservationtours.Services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourController {

    TourService service;

    @Autowired
    public TourController(TourService service){
        this.service = service;
    }
}
