package com.aaa.automaticanalyzer.api.chat.domain;

import com.aaa.automaticanalyzer.model.SimplifiedUser;
import lombok.Data;

import java.util.List;

@Data
public class ChatRestOutput {
    private long id;

    private SimplifiedUser user;

    private String nursingSpeciality;

    private List<MessageRestOutput> messages;

    private long createdDate;
}
