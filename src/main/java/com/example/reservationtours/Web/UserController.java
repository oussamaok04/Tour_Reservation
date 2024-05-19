package com.example.reservationtours.Web;

import com.example.reservationtours.Services.TourService;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }
}
