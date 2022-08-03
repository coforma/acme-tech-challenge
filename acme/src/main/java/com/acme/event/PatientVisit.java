package com.acme.event;

import com.acme.patient.Patient;
import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Data
@Entity
@Table(name = "patient_visit")
public class PatientVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(targetEntity=Patient.class,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Patient> patients = new ArrayList<>();

}