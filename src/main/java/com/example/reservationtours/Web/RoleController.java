package com.example.reservationtours.Web;

import com.example.reservationtours.Services.ReservationService;
import com.example.reservationtours.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    RoleService service;

    @Autowired
    public RoleController(RoleService service){
        this.service = service;
    }
}
