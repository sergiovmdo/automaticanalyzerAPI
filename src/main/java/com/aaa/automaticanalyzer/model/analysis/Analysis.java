package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.model.Disease;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ANALYSIS")
@Data
public class Analysis {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Disease disease;
    private Long date;

    private String analysisData;

}
