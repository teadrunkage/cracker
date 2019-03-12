package ru.ncedu.schek.cracker.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Admin on 13.03.2019.
 */
/*Существующая инфраструктура может создать ограничения для развертывания  WebSocket,
 обычно  HTTP использует порт  80 & 443, поэтому  WebSocket должен использовать другие порты,
  при этом почти все Firewall предотвращающая порты отличающиеся от 80 & 443,
   используя  Proxy (уполномоченный) так же происходят разные проблемы.
    Поэтому чтобы легко можно развертывать, WebSocket использует  HTTP Handshake для обновления.
     Это означает в первый раз когда client отправляет запрос на основе  HTTP к  server, говорит  server что это не  HTTP,
      обновить на  WebSocket, таким образом они формируют соединение.
Класс  HttpHandshakeInterceptor используется для разрешения событий сразу перед и после того,
 как WebSocket пожал руку с  HTTP.*/

@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        logger.info("Call beforeHandshake");

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            attributes.put("sessionId", session.getId());
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, @Nullable Exception e) {
        logger.info("Call afterHandshake");
    }
}
