package com.aaa.automaticanalyzer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CHATS")
@Data
public class Chat {
    @Column(unique = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    private NursingSpeciality nursingSpeciality;

    @ManyToMany
    private List<Message> messages;

}
