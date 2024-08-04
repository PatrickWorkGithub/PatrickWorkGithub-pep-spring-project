package com.example.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.*;

import com.example.entity.*;
import com.example.service.*;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        //this.accountService = new AccountService();
        //this.messageService = new MessageService();
        this.accountService = accountService;
        this.messageService = messageService;
        //accountService = aS;
        //messageService = mS;
    }
    
    @PostMapping(value = "/register")
    public @ResponseBody ResponseEntity postRegisterHandler(@RequestBody Account account){
        if(accountService.newUsername(account)!=null){
            return ResponseEntity.status(409).body("Duplicate Username");
        } else{
            Account registeredAccount = accountService.registerAccount(account);
            if(registeredAccount==null){
                return ResponseEntity.status(400).body("Invalid Registration");
            }else {
                return ResponseEntity.status(200).body(registeredAccount);
            }
        }
        
    }

    @PostMapping(value = "/login")
    public @ResponseBody ResponseEntity postLoginHandler(@RequestBody Account account){
        Account loggedIn = accountService.loginAccount(account);
        if(loggedIn==null){
            return ResponseEntity.status(401).body(null);
        }else{
            return ResponseEntity.status(200).body(loggedIn);
        }
    }

    @PostMapping(value = "/messages")
    public @ResponseBody ResponseEntity postMessagesHandler(@RequestBody Message message){
        Message postedMessage = messageService.postMessage(message);
        if(postedMessage==null){
            return ResponseEntity.status(400).body(null);
        }else{
            return ResponseEntity.status(200).body(postedMessage);
        }
    }

    @GetMapping(value = "/messages")
    public @ResponseBody ResponseEntity getAllMessagesHandler(){
        //List<Message> messages = messageService.postMessage(message);
        return ResponseEntity.status(200).body(messageService.getAllMessages());
    }

    @GetMapping(value = "/messages/{message_id}")
    public @ResponseBody ResponseEntity getMessageByIdHandler(@PathVariable int message_id){
        
        Message message = messageService.getMessageById(message_id);
        if(message == null){
            return ResponseEntity.status(200).body("");
        }
        else{
            return ResponseEntity.status(200).body(message);
        }
    }

    @DeleteMapping(value = "/messages/{message_id}")
    public @ResponseBody ResponseEntity deleteMessageHandler(@PathVariable int message_id){
        int rows = messageService.deleteMessage(message_id);
        if(rows>0){
            return ResponseEntity.status(200).body(rows);
        } else{
            return ResponseEntity.status(200).body("");
        }
        
    }

     
    @PatchMapping(value = "/messages/{message_id}")
    public @ResponseBody ResponseEntity patchMessageHandler(@RequestBody String messageText, @PathVariable int message_id){
        int rows = messageService.patchMessage(message_id,messageText);
        if(rows>0){
            return ResponseEntity.status(200).body(rows);
        }else{
    
            return ResponseEntity.status(400).body("");
        }
    }
    
    @GetMapping(value = "/accounts/{account_id}/messages")
    public @ResponseBody ResponseEntity getAccountMessagesHandler(@PathVariable int account_id){
        return ResponseEntity.status(200).body(messageService.getAccountMessages(account_id));
    }
    /**/
}
