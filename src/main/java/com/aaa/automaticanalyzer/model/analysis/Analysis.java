package com.aaa.automaticanalyzer.model.analysis;

import com.aaa.automaticanalyzer.model.Disease;
import com.aaa.automaticanalyzer.model.User;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.tuple.GenerationTiming;

import javax.persistence.*;

@Entity
@Table(name = "ANALYSIS")
@Data
public class Analysis {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    private Disease disease;
    private Long date;

    private String analysisData;

}
