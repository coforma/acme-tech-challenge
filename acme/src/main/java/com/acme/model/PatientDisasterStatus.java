package com.acme.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "patientdisaster")
public class PatientDisasterStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patientid", referencedColumnName = "id")
    private Patient patient;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "disasterid", referencedColumnName = "id")
    private Disaster disaster;
    
    private Date date;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    private PatientStatus patientStatus;

}