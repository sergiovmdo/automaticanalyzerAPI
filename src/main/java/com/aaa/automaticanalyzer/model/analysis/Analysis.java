package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.model.Disease;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANALYSIS")
@Data
public class Analysis {
    @Column(unique = true)
    @Id
    private String id;

    private String userDNI;


}
