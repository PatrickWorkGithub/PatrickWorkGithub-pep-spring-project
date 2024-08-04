package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("FROM Message WHERE messageId = :messageIdVar")
    Message getMessageById(@Param("messageIdVar") int messageId);

    @Modifying
    @Query("DELETE FROM Message WHERE messageId = :messageIdVar")
    int deleteMessageById(@Param("messageIdVar") int messageId);

    @Modifying
    @Query("UPDATE Message set messageText = :messageTextVar WHERE messageId = :messageIdVar")
    int patchMessage(@Param("messageIdVar") int messageId, @Param("messageTextVar") String messageText);

    @Query("FROM Message WHERE postedBy = :accountIdVar")
    List<Message> getAccountMessages(@Param("accountIdVar") int accountId);

    
}
