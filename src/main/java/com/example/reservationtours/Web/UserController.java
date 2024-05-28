package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    //Retourne la list des users
    @GetMapping("/admin/users/all")
    public String getAllUsers(Model model){
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }

    //Supprimer un user
    @GetMapping("/admin/users/delete")
    public String deleteUser(@RequestParam("id") Long id){
        service.DeleteUser(id);
        return "redirect:/admin/users/all";
    }
}
