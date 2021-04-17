package com.aaa.automaticanalyzer.api.chat.rest.mapping;

import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.model.Chat;
import com.aaa.automaticanalyzer.model.Message;
import com.aaa.automaticanalyzer.model.NursingSpeciality;

import java.util.Arrays;

public class ChatMapper {
    public static Chat createChatFromRestInput(ChatRestInput chatRestInput) {
        Chat chat = new Chat();
        //TODO: Parse the string from rest input to the enum as it is done with diseases
        chat.setNursingSpeciality(NursingSpeciality.CUIDADOSMEDICOQUIRURGICOS);

        return chat;
    }

    public static Message createMessageFromRestInput(MessageRestInput messageRestInput){
        Message message = new Message();
        message.setContent(messageRestInput.getContent());
        return message;
    }
}
