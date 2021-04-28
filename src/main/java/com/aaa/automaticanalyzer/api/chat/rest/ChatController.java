package com.aaa.automaticanalyzer.api.chat.rest;

import com.aaa.automaticanalyzer.api.chat.business.ChatService;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestOutput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.common.AuthAwareRestController;
import com.aaa.automaticanalyzer.model.Chat.Chat;
import com.aaa.automaticanalyzer.model.Chat.SimplifiedChat;
import com.aaa.automaticanalyzer.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
@AuthAwareRestController
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody final ChatRestInput chatRestInput, final User user){
        return chatService.createChat(chatRestInput, user);
    }

    @PostMapping("/message")
    public ResponseEntity<Void> createMessage(@RequestBody final MessageRestInput messageRestInput, final User user){
        return chatService.createMessage(messageRestInput, user);
    }

    @GetMapping("/{id}")
    public ChatRestOutput getChat(@PathVariable("id") Long id){
        return chatService.getChat(id);
    }

    @GetMapping
    public List<SimplifiedChat> getChats(final User user){
        return chatService.getChats(user);
    }
}
