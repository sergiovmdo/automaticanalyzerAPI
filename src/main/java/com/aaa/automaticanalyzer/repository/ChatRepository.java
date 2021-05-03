package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.chat.Chat;
import com.aaa.automaticanalyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    public List<Chat> getChatsByUserOrderByCreatedDateDesc(final User user);
}
