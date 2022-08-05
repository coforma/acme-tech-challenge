package com.acme.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Disaster")
public class Disaster implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7381238922978596082L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "startDate", nullable = false)
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @Column(nullable = false)
    private String name;
}