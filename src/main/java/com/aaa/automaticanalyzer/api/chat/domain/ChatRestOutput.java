package com.aaa.automaticanalyzer.api.chat.domain;

import com.aaa.automaticanalyzer.model.Chat.Message;
import com.aaa.automaticanalyzer.model.NursingSpeciality;
import com.aaa.automaticanalyzer.model.SimplifiedUser;
import com.aaa.automaticanalyzer.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class ChatRestOutput {
    private long id;

    private SimplifiedUser user;

    private NursingSpeciality nursingSpeciality;

    private List<MessageRestOutput> messages;

    private long createdDate;
}
