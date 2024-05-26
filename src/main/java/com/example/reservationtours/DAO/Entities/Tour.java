package com.example.reservationtours.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Tour {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_tour;

    private String titre;

    private String description;

    @Temporal(TemporalType.TIME)
    private Date duree;

    private boolean isDisponible;

    private int nombre_place;

    @Temporal(TemporalType.DATE)
    private Date date;

    private double prix;

    private float evaluation;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();
}
