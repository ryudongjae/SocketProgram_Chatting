package com.example.chatting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    public enum MessageType {
        ENTER,TALK
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}
