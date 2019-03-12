package ru.ncedu.schek.cracker.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import ru.ncedu.schek.cracker.forms.ChatMessage;

/**
 * Created by Admin on 13.03.2019.
 */
@Controller
public class ChatController {
    /*В классе ChatController есть методы,
    отвечающие за получение сообщения от одного клиента и трансляцию его всем остальным.
    Добавление пользователя и его сообщения транслируются всем, кто подключен к чату:*/

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUsername());
        return chatMessage;
    }
}
