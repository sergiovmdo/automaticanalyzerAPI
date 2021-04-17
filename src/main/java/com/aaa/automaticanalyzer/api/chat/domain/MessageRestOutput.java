package com.aaa.automaticanalyzer.api.chat.domain;

import com.aaa.automaticanalyzer.model.SimplifiedUser;
import com.aaa.automaticanalyzer.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class MessageRestOutput {
    private long id;

    private String content;

    private SimplifiedUser user;

    private long createdDate;
}
