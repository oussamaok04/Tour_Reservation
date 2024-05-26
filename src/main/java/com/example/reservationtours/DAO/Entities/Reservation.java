package com.example.reservationtours.DAO.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reservation;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tour tour;

    @Temporal(TemporalType.DATE)
    private Date date_reservation;
}
