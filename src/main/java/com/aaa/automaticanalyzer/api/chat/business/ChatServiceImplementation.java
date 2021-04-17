package com.aaa.automaticanalyzer.api.chat.business;

import com.aaa.automaticanalyzer.api.chat.domain.ChatRestInput;
import com.aaa.automaticanalyzer.api.chat.domain.MessageRestInput;
import com.aaa.automaticanalyzer.api.chat.rest.mapping.ChatMapper;
import com.aaa.automaticanalyzer.model.Chat.Chat;
import com.aaa.automaticanalyzer.model.Chat.Message;
import com.aaa.automaticanalyzer.model.Chat.SimplifiedChat;
import com.aaa.automaticanalyzer.model.User;
import com.aaa.automaticanalyzer.notifications.MessagingService;
import com.aaa.automaticanalyzer.notifications.NotificationType;
import com.aaa.automaticanalyzer.repository.ChatRepository;
import com.aaa.automaticanalyzer.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
        chat.setDate(Calendar.getInstance().getTimeInMillis());
        Message message = new Message();
        message.setContent(chatRestInput.getDescription());
        message.setUser(user);
        message.setDate(Calendar.getInstance().getTimeInMillis());
        messageRepository.save(message);
        chat.setMessages(Arrays.asList(message));
        chatRepository.save(chat);
        return ResponseEntity.ok(chat);
    }

    @Override
    public ResponseEntity<Void> createMessage(MessageRestInput messageRestInput, final User user) {
        Chat chat = getChat(messageRestInput.getChatId());
        Message message = ChatMapper.createMessageFromRestInput(messageRestInput);
        message.setUser(user);
        message.setDate(Calendar.getInstance().getTimeInMillis());
        messageRepository.save(message);
        chat.getMessages().add(message);

        chatRepository.save(chat);
        if (!chat.getUser().equals(user)) {
            messagingService.notifyUser(chat.getUser(), NotificationType.CHAT.getNotificationTitle(), message.getContent(), NotificationType.CHAT);
        }

        return ResponseEntity.ok(null);
    }

    @Override
    public Chat getChat(Long id) {
        return chatRepository.findById(id).get();
    }

    @Override
    public List<SimplifiedChat> getChats(final User user) {
        return chatRepository.getChatsByUser(user).stream().map(chat -> {
            SimplifiedChat simplifiedChat = new SimplifiedChat();
            simplifiedChat.setId(chat.getId());
            simplifiedChat.setNursingSpeciality(chat.getNursingSpeciality());
            simplifiedChat.setLastMessageContent(chat.getMessages().get(chat.getMessages().size() - 1).getContent());
            return simplifiedChat;
        }).collect(Collectors.toList());
    }
}
