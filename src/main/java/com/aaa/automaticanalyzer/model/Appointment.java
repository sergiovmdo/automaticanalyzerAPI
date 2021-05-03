package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "APPOINTMENTS")
@Data
public class Appointment {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Disease disease;

    private Long date;

    private String location;
}
