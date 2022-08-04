package com.acme.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "statecodes")
public class StateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String code;
    private String name;
    
}