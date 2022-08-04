package com.acme.model;

import javax.persistence.*;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Disaster")
public class Disaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    private Date endDate;
    @Column(name = "name", nullable = false)
    private String name;
}