package com.aaa.automaticanalyzer.api.chat.business;

import com.aaa.automaticanalyzer.Strings;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.ChatRestOutput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.api.chat.rest.mapping.ChatMapper;
import com.aaa.automaticanalyzer.model.chat.Chat;
import com.aaa.automaticanalyzer.model.chat.Message;
import com.aaa.automaticanalyzer.model.chat.SimplifiedChat;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import com.aaa.automaticanalyzer.repository.ChatRepository;
import com.aaa.automaticanalyzer.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ChatServiceImplementation implements ChatService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final MessagingService messagingService;

    @Override
    public ResponseEntity<Chat> createChat(ChatRestInput chatRestInput, final User user) {
        Chat chat = ChatMapper.createChatFromRestInput(chatRestInput);
        chat.setUser(user);
        chat.setCreatedDate(Calendar.getInstance().getTimeInMillis());
        Message message = new Message();
        message.setContent(chatRestInput.getDescription());
        message.setUser(user);
        message.setCreatedDate(Calendar.getInstance().getTimeInMillis());
        messageRepository.save(message);
        chat.setMessages(Arrays.asList(message));
        chatRepository.save(chat);
        return ResponseEntity.ok(chat);
    }

    @Override
    public ResponseEntity<Void> createMessage(MessageRestInput messageRestInput, final User user) {
        Chat chat = chatRepository.getOne(messageRestInput.getChatId());
        Message message = ChatMapper.createMessageFromRestInput(messageRestInput);
        message.setUser(user);
        message.setCreatedDate(Calendar.getInstance().getTimeInMillis());
        messageRepository.save(message);
        chat.getMessages().add(message);

        chatRepository.save(chat);
        if (!chat.getUser().equals(user)) {
            messagingService.notifyNewChatMessage(chat.getUser(), message, chat.getId());
        }

        return ResponseEntity.ok(null);
    }

    @Override
    public ChatRestOutput getChat(Long id, final User user)  {
        Optional<Chat> optChat = chatRepository.findById(id);
        if (optChat.isPresent()) {
            Chat chat = chatRepository.findById(id).get();
            return ChatMapper.createOutputFromChat(chat, user.getLanguage());
        }
        return null;
    }

    @Override
    public List<SimplifiedChat> getChats(final User user) {
        return chatRepository.getChatsByUserOrderByCreatedDateDesc(user).stream().map(chat -> {
            SimplifiedChat simplifiedChat = new SimplifiedChat();
            simplifiedChat.setId(chat.getId());
            simplifiedChat.setNursingSpeciality(Strings.getStringFromObject(chat.getNursingSpeciality()).getLanguage(user.getLanguage()));
            simplifiedChat.setLastMessageContent(chat.getMessages().get(chat.getMessages().size() - 1).getContent());
            return simplifiedChat;
        }).collect(Collectors.toList());
    }
}
