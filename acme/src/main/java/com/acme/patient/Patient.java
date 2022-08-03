package com.acme.patient;

import com.acme.event.PatientVisit;
import lombok.Data;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.CascadeType.ALL;

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

    @ManyToOne(optional = false)
    @JoinColumn(name="patient_visit_id", nullable=false)
    private PatientVisit patientVisit;

    private String PatientIdFacility;
    private Integer FacilityNPI;

}