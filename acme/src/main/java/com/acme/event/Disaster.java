package com.acme.event;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "disaster")
public class Disaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date startDate;
    private Date endDate;
    private String name;
    private Integer incidentTypeId;
    private Integer declarationTypeId;

}