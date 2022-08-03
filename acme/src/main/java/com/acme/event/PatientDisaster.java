package com.acme.event;

import com.acme.patient.Patient;
import com.acme.patient.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "patient_disaster")
public class PatientDisaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    private Long disasterId;
    private Date date;
    private Long statusId;

}