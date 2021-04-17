package com.aaa.automaticanalyzer.model.Chat;

import com.aaa.automaticanalyzer.model.NursingSpeciality;
import com.aaa.automaticanalyzer.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class SimplifiedChat {
    private long id;
    private NursingSpeciality nursingSpeciality;
    private String lastMessageContent;
}
