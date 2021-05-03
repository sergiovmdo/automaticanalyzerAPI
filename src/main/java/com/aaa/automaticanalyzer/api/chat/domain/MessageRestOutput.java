package com.aaa.automaticanalyzer.api.chat.domain;

import com.aaa.automaticanalyzer.model.SimplifiedUser;
import lombok.Data;

@Data
public class MessageRestOutput {
    private long id;

    private String content;

    private SimplifiedUser user;

    private long createdDate;
}
