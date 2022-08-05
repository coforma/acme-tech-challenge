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
    @Column(nullable = false)
    private Long id;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @Column(nullable = false)
    private String name;
}