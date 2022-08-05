package com.acme.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "PatientStatus")
public class PatientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String status;

}