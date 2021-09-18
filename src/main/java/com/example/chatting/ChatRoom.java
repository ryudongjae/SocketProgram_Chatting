package com.example.chatting;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handlerAction(WebSocketSession  webSocketSession,
                              Message message , ChatService chatService){
        if(message.getMessageType().equals(Message.MessageType.ENTER)){
            sessions.add(webSocketSession);
            message.setMessage(message.getSender() + "님이 입장함");
        }

        sendMessage(message,chatService);
    }

    private <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session->chatService.sendMessage(session,message));
    }
}
