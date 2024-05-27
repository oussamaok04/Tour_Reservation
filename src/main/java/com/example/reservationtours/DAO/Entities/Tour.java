package com.example.reservationtours.DAO.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

//    @Temporal(TemporalType.TIME)
//    @DateTimeFormat(pattern = "MM/DD/YYYY")
    @Pattern(regexp = "^\\d{1,2}:\\d{2}$", message = "Duration must be in the format hh:mm:ss")
    private String duree;

    private boolean isDisponible;

    private int nombre_place;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private double prix;

    private float evaluation;

    @OneToMany
    private List<Reservation> reservations = new ArrayList<>();
}
