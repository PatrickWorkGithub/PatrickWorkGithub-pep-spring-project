package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.*;

@Service
public class AccountService {
    AccountRepository accountRepository;
    MessageRepository messageRepository;

    @Autowired
    public AccountService(AccountRepository accountRepo, MessageRepository messageRepo){
        accountRepository = accountRepo;
        messageRepository = messageRepo;
    }
    public Account registerAccount(Account account){
        if(account.getUsername().length()>0 && account.getPassword().length() >=4 ){
            Account registered = accountRepository.save(account);
            return registered;
        }
        return null;
    }
    public Account loginAccount(Account account){
        return accountRepository.login(account.getUsername(),account.getPassword());
    }
    public Account newUsername(Account account){
        return accountRepository.duplicateCheck(account.getUsername());
    }
}
