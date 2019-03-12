package ru.ncedu.schek.cracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created by Admin on 13.03.2019.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //надо настроить конечную точку и брокер сообщений
    /*мы регистрируем конечную точку, которую клиенты будут использовать,
     чтобы подключиться к нашему Websocket-серверу.
     SockJS – для браузеров, которые не поддерживают Websocket.*/

    /*STOMP – это Simple Text Oriented Messaging Protocol.
    Это протокол обмена сообщениями, задающий формат и правила обмена.*/

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    /*мы настраиваем брокер сообщений,
    который будет использоваться для направления сообщений от одного клиента к другому*/

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");//все сообщения от клиентов, направленные по адресу
        registry.enableSimpleBroker("/topic");


        //   Use this for enabling a Full featured broker like RabbitMQ

        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }
}
