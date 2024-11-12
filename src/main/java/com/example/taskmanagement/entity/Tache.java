package com.example.taskmanagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date datedebut;

    @Temporal(TemporalType.DATE)
    private Date datefin;

    @Enumerated(EnumType.STRING)
    private Etat etat;

    private String priorite;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
