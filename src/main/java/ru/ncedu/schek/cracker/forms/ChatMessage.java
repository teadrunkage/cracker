package ru.ncedu.schek.cracker.forms;

/**
 * Created by Admin on 13.03.2019.
 */
public class ChatMessage {
    private String username;
    private String message;
    private MessageType type;

    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
    @Override
    public String toString() {
        return "ChatMessage [user=" + username + ", message=" + message + "]";
    }
}
