package com.acme.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * Instantiates a new user.
 */
@Data
@Entity
@Table(name = "User")
public class User {
	/** The name. */
	@Id    
	private String name;
	
	/** The password. */
	private String password;
	
	/** The roles. */
	private String roles;
    
    /** The facility. */
    @ManyToOne
    @JoinColumn(name = "facilityNpi", referencedColumnName = "npi")
    private Facility facility;
}
