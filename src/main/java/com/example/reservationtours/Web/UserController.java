package com.example.reservationtours.Web;

import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.RoleService;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    UserService service;
    RoleService roleService;

    @Autowired
    public UserController(UserService service, RoleService roleService){
        this.service = service;
        this.roleService = roleService;
    }

    //Retourne la list des users
    @GetMapping("/admin/users/all")
    public String getAllUsers(Model model){
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "listUsers";
    }

    //Ajouter un user
    @GetMapping("/admin/users/save")
    public String addUsers(Model model){
        return "addUser";
    }

    @PostMapping("/admin/users/save")
    public String addUsers(Model model, @ModelAttribute("u") User user){
        user.setId_user(null);
        Role userRole = roleService.findByRoleName("USER");
        user.getRoles().add(userRole);
        service.addUser(user);
        return "redirect:/admin/users/all";
    }

    //Supprimer un user
    @GetMapping("/admin/users/delete")
    public String deleteUser(@RequestParam("id") Long id){
        service.DeleteUser(id);
        return "redirect:/admin/users/all";
    }
}
