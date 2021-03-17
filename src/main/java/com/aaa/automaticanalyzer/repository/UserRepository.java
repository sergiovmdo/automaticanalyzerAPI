package com.aaa.automaticanalyzer.repository;

import com.aaa.automaticanalyzer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
