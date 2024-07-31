package com.example.repository;

import Lab.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Long> {
}
