package com.aaa.automaticanalyzer.api.chat.rest.mapping;

import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestOutput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestOutput;
import com.aaa.automaticanalyzer.model.Chat.Chat;
import com.aaa.automaticanalyzer.model.Chat.Message;
import com.aaa.automaticanalyzer.model.NursingSpeciality;
import com.aaa.automaticanalyzer.model.SimplifiedUser;

import java.util.stream.Collectors;

public class ChatMapper {
    public static Chat createChatFromRestInput(ChatRestInput chatRestInput) {
        Chat chat = new Chat();
        //TODO: Parse the string from rest input to the enum as it is done with diseases
        chat.setNursingSpeciality(NursingSpeciality.CUIDADOSMEDICOQUIRURGICOS);

        return chat;
    }

    public static Message createMessageFromRestInput(MessageRestInput messageRestInput) {
        Message message = new Message();
        message.setContent(messageRestInput.getContent());
        return message;
    }

    public static ChatRestOutput createOutputFromChat(Chat chat) {
        ChatRestOutput chatRestOutput = new ChatRestOutput();
        chatRestOutput.setId(chat.getId());
        chatRestOutput.setCreatedDate(chat.getCreatedDate());
        chatRestOutput.setNursingSpeciality(chat.getNursingSpeciality());
        chatRestOutput.setUser(SimplifiedUser.fromUser(chat.getUser()));
        chatRestOutput.setMessages(chat.getMessages().stream().map(ChatMapper::createOutputFromMessage).collect(Collectors.toList()));

        return chatRestOutput;
    }

    private static MessageRestOutput createOutputFromMessage(Message message) {
        MessageRestOutput messageRestOutput = new MessageRestOutput();
        messageRestOutput.setContent(message.getContent());
        messageRestOutput.setCreatedDate(message.getCreatedDate());
        messageRestOutput.setId(message.getId());
        messageRestOutput.setUser(SimplifiedUser.fromUser(message.getUser()));
        return messageRestOutput;
    }

}
