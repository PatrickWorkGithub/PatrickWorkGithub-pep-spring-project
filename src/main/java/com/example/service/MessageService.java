package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.*;
import com.example.service.AccountService;

@Service
public class MessageService {
    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public MessageService(AccountRepository accountRepo, MessageRepository messageRepo){
        accountRepository = accountRepo;
        messageRepository = messageRepo;
    }
    public Message postMessage(Message message){
        if(message.getMessageText().length()>0 && message.getMessageText().length()<=255){
            if(accountRepository.accountExists(message.getPostedBy()) != null){
                return messageRepository.save(message);
            }
        }
        return null;
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    @Transactional
    public Message getMessageById(int message_id){
        return messageRepository.getMessageById(message_id);
    }

    /* */
    @Transactional
    public int deleteMessage(int message_id){
        return messageRepository.deleteMessageById(message_id);
    }
    @Transactional
    public int patchMessage(int message_id, String message_text){
        int rows = 0;
        if(message_text.length()>0 && message_text.length()<=255 && !message_text.equals("{\"messageText\": \"\"}")){
        
            rows = messageRepository.patchMessage(message_id,message_text);
        }

        return rows;
    }
    public List<Message> getAccountMessages(int account_id){
        return messageRepository.getAccountMessages(account_id);
    }
    
}
