package com.aaa.automaticanalyzer.api.chat.business;

import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestOutput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.model.chat.Chat;
import com.aaa.automaticanalyzer.model.chat.SimplifiedChat;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {
    public ResponseEntity<Chat> createChat(ChatRestInput chatRestInput, final User user);

    public ResponseEntity<Void> createMessage(MessageRestInput messageRestInput, final User user);

    public ChatRestOutput getChat(Long id, final User user);

    public List<SimplifiedChat> getChats(final User user);
}
