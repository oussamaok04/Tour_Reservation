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
@Table(name = "the_user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;
    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}
