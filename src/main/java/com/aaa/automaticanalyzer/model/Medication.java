package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MEDICATION")
@Data
public class Medication {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<Medicine> medicines;

    private Disease disease;

    private boolean needsRevision = false;
}
