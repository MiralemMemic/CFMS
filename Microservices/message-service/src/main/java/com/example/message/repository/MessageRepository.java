package com.example.message.repository;

import com.example.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver(long receiver);
    List<Message> findBySender(long sender);
}
