package com.aaa.automaticanalyzer.api.chat.domain;

import lombok.Data;

@Data
public class MessageRestInput {
    private Long chatId;
    private String content;
}
