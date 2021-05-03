package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MEDICINE")
@Data
public class Medicine {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private Double dose;
}
