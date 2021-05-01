package com.aaa.automaticanalyzer.api.chat.rest.mapping;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestOutput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestOutput;
import com.aaa.automaticanalyzer.model.Chat.Chat;
import com.aaa.automaticanalyzer.model.Chat.Message;
import com.aaa.automaticanalyzer.model.Language;
import com.aaa.automaticanalyzer.model.NursingSpeciality;
import com.aaa.automaticanalyzer.model.SimplifiedUser;

import java.util.stream.Collectors;

public class ChatMapper {
    public static Chat createChatFromRestInput(ChatRestInput chatRestInput) {
        Chat chat = new Chat();
        Strings speciality = Strings.getObjectFromString(chatRestInput.getType());
        chat.setNursingSpeciality(getFromStrings(speciality));

        return chat;
    }

    private static NursingSpeciality getFromStrings(Strings speciality){
       return NursingSpeciality.valueOf(speciality.name());
    }

    public static Message createMessageFromRestInput(MessageRestInput messageRestInput) {
        Message message = new Message();
        message.setContent(messageRestInput.getContent());
        return message;
    }

    public static ChatRestOutput createOutputFromChat(Chat chat, Language language) {
        ChatRestOutput chatRestOutput = new ChatRestOutput();
        chatRestOutput.setId(chat.getId());
        chatRestOutput.setCreatedDate(chat.getCreatedDate());
        chatRestOutput.setNursingSpeciality(Strings.getStringFromObject(chat.getNursingSpeciality()).getLanguage(language));
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
