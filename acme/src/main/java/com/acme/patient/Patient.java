package com.acme.patient;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "patient")
public class Patient{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    private String DateOfDeath;
}