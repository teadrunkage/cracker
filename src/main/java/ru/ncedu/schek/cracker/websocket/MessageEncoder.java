package ru.ncedu.schek.cracker.websocket;

import com.google.gson.Gson;
import ru.ncedu.schek.cracker.forms.ChatMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Admin on 16.03.2019.
 */
public class MessageEncoder implements Encoder.Text<ChatMessage>{
    @Override
    public String encode(ChatMessage message) throws EncodeException {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        return json;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
