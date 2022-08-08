package com.acme.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table (name = "Facility")
public class Facility implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4663074549449681578L;
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
    
    @OneToOne
    @JoinColumn(name = "stateCode", referencedColumnName = "id")
    private StateCode stateCode;
}