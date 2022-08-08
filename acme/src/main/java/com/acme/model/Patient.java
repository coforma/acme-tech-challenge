package com.acme.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Patient")
public class Patient implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3754080050968439373L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "facilityNpi", referencedColumnName = "npi")
    private Facility facility;
    
    @Column(name = "patientIdFromFacility", nullable = false)
    private String patientIdFromFacility;
    
}