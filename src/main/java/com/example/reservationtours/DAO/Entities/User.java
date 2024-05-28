package com.example.reservationtours.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "user_app")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;
    private String username;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
