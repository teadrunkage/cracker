package ru.ncedu.schek.cracker.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import ru.ncedu.schek.cracker.forms.ChatMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Map;

/**
 * Created by Admin on 16.03.2019.
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    private Gson gson = new Gson();

    @MessageMapping("/message")
    @SendToUser("/queue/reply")
    public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {
        return gson.fromJson(message, Map.class).get("name").toString();
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) throws URISyntaxException, IOException {
      /*  GreetClient client1 = new GreetClient();
        client1.startConnection("127.0.0.1", 5555);

       // //final ChatEndpoint clientEndPoint = new ChatEndpoint(new URI("ws://localhost:8081/chat"));
            MessageEncoder message = new MessageEncoder();
            try {
                client1.sendMessage(message.encode(chatMessage));
            } catch (EncodeException e) {
                e.printStackTrace();
            }
            */
        return chatMessage;
    }
    //подписка на тему
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
