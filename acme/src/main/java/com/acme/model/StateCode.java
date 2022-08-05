package com.acme.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "StateCode")
public class StateCode implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5015747891912729798L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String code;
    private String name;
    
}