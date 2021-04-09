package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MEDICINE")
@Data
public class Medicine {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    private Medication medication;

    private String name;

    private Double dose;


}
