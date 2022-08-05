package com.acme.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table (name = "Facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String npi;
    @Column(name="facilityName")
    private String facilityName;
    @Column(name = "isOpen", nullable = false)
    private Boolean isOpen;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "stateCode", referencedColumnName = "id")
    private StateCode stateCode;
}