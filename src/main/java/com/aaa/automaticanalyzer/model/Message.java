package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@Data
public class Message {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String content;

    @ManyToOne
    private User user;
}
