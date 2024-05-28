package com.example.reservationtours.Security.Controller;

import com.example.reservationtours.Services.RoleService;
import com.example.reservationtours.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class SecurityController {

    RoleService roleService;
    UserService userService;

    @GetMapping("/security/403")
    public String accessDenied(){
        return "forbidden";
    }

    @GetMapping("/login")
    public String authenticate(){
        return "login";
    }
}
