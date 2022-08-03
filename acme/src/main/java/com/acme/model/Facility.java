package com.acme.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String npi;
    private String facilityName;
    private Boolean isOpen;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "statecode", referencedColumnName = "id")
    private StateCode stateCode;
}