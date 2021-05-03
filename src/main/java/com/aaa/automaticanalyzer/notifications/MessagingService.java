package com.aaa.automaticanalyzer.notifications;

import com.aaa.automaticanalyzer.model.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessagingService {

    @Async
    public void notifyUser(final User user, String title, String body, NotificationType category) {
        if (user.getFirebaseToken() != null) {
            try {
                Message message = Message.builder().setToken(user.getFirebaseToken())
                        .putData("title", title)
                        .putData("body", body)
                        .putData("category", category.name())
                        .build();
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Async
    public void notifyNewChatMessage(final User user, com.aaa.automaticanalyzer.model.chat.Message message, Long chatId) {
        if (user.getFirebaseToken() != null) {
            try {
                Message notification = Message.builder().setToken(user.getFirebaseToken())
                        .putData("content", message.getContent())
                        .putData("user", user.getName())
                        .putData("chatId", String.valueOf(chatId))
                        .putData("category", NotificationType.CHAT.name())
                        .build();
                FirebaseMessaging.getInstance().send(notification);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
