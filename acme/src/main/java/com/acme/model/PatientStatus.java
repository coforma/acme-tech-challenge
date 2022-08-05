package com.acme.model;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;
@Data
@Entity
@Table(name = "PatientStatus")
public class PatientStatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6382860280352796997L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String status;

}