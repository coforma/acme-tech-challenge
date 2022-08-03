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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patientid", referencedColumnName = "id")
    private Patient patient;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disasterid", referencedColumnName = "id")
    private Disaster disaster;
    
    private Date date;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    private Patient patientStatus;

}