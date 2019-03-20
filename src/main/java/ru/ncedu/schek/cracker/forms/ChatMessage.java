package ru.ncedu.schek.cracker.forms;

/**
 * Created by Admin on 13.03.2019.
 */
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String to;


    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
