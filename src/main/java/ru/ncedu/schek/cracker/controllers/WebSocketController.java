package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import ru.ncedu.schek.cracker.bot.SimpleBot;
import ru.ncedu.schek.cracker.forms.ChatMessage;

import javax.websocket.EncodeException;
import java.net.URISyntaxException;

/**
 * Created by Admin on 16.03.2019.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws URISyntaxException, EncodeException {
        return chatMessage;
    }

    
    @MessageMapping("/chat.answerByBot")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage answerByBot(@Payload ChatMessage chatMessage) throws URISyntaxException {
    	SimpleBot bot = new SimpleBot();
    	String text = chatMessage.getContent();
    	String answer = bot.sayInReturn(text, true);
    	chatMessage.setContent(answer);
    	chatMessage.setSender("bot");
    	return chatMessage;
    }
    
    
    private static String getMessage(String message) {
        return Json.createObjectBuilder()
                .add("user", "bot")
                .add("message", message)
                .build()
                .toString();
    }
  
    //подписка на тему
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
