package com.acme.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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
    private LocalDateTime startDate;
    @Column(name="endDate")
    private LocalDateTime endDate;
    @Column(nullable = false)
    private String name;
}