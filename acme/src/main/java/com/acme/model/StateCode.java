package com.acme.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "StateCode")
public class StateCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String code;
    private String name;
    
}