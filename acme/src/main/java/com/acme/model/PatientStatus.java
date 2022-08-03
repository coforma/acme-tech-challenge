package com.acme.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "patientstatus")
public class PatientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String status;

}