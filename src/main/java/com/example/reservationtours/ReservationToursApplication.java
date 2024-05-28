package com.example.reservationtours;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.Tour;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.DAO.Repositories.ReservationRepository;
import com.example.reservationtours.DAO.Repositories.RoleRepository;
import com.example.reservationtours.DAO.Repositories.TourRepository;
import com.example.reservationtours.DAO.Repositories.UserRepository;
import com.example.reservationtours.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ReservationToursApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationToursApplication.class, args);
    }

    //Adding random tours to database
    //Database Url is in 'application.properties' file
    @Bean
    CommandLineRunner commandLineRunner(TourRepository tRepo,
                                        ReservationRepository rRepo,
                                        RoleRepository roleRepo,
                                        UserRepository uRepo,
                                        UserService uservice){
        return args ->{
            //Create Role "ADMIN"
            Role role1 = new Role();
            role1.setId_role(null);
            role1.setRoleName("ADMIN"); //Nom du role
            roleRepo.save(role1);

            //Create Role "USER"
            Role role2 = new Role();
            role2.setId_role(null);
            role2.setRoleName("USER"); //Nom du role
            roleRepo.save(role2);

            //Create Admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("1234");
            admin.getRoles().add(role1);
            admin.getRoles().add(role2);
            uservice.addUser(admin);

            //Create User
            User user = new User();
            user.setUsername("user1");
            user.setPassword("1234");
            user.getRoles().add(role2);
            uservice.addUser(user);
//            User user2 = new User();
//            user.setUsername("user2");
//            user.setPassword("1234");
//            user.getRoles().add(role2);
//            uservice.addUser(user);
            for (int i = 0; i < 15; i++) {
//                Create Tour
                Tour t1 = new Tour();
                t1.setId_tour(null);
                t1.setTitre("Tour"+i);
                t1.setDescription("Desc de tour "+i);
                t1.setDisponible(Math.random()>0.5?true:false);
                t1.setNombre_place((int)Math.floor(Math.random() * 10));
                t1.setDate(new Date());
                t1.setPrix(150.0);
                t1.setDuree("02:00");
                tRepo.save(t1);

 //               Create User
                User u1 = new User();
                u1.setId_user(null);
                if (Math.random()>0.5){
                    //Insert role "USER" and "ADMIN"
                    u1.getRoles().add(role1);
                    u1.getRoles().add(role2);
                } else {
                    //Insert role "USER"
                    u1.getRoles().add(role2);
                }
                u1.setUsername("Oussama"+i);
                u1.setEmail("o.ok"+i+"@gmail.com");
                u1.setPassword("123456789");
                uRepo.save(u1);

                for (Role r: u1.getRoles()) {
                    if (r.getRoleName().equals("ADMIN")) {
                        role2.getUsers().add(u1); //Insert u1 in role "USER"
                        role1.getUsers().add(u1); //Insert u1 in role "ADMIN"
                        roleRepo.save(role2);
                    } else {
                        role2.getUsers().add(u1);
                        roleRepo.save(role1);
                    }
                }

                //Create Reservation
                Reservation r1 = new Reservation();
                r1.setId_reservation(null);
                r1.setDate_reservation(new Date());
                r1.setTour(t1);
                r1.setUser(user);
                rRepo.save(r1);

                //Add Reservation to Tour
                t1.getReservations().add(r1);
                tRepo.save(t1);
            }

        };
    }

}
