package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, String> {
}
