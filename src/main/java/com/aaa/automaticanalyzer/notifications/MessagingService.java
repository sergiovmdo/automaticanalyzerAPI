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
    public void notifyUser(final User user, String title, String body) {
        if (user.getFirebaseToken() != null) {
            try {
                Message message = Message.builder().setToken(user.getFirebaseToken())
                        .putData("title", title)
                        .putData("body", body)
                        .build();
                FirebaseMessaging.getInstance().send(message);
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
