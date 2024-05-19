package com.example.reservationtours.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_role;
    private String role;
    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();

}
