package com.panda.ChatAPP.cont;

import com.panda.ChatAPP.model.ChatMessage;
import com.panda.ChatAPP.model.ChatMessageNotification;
import com.panda.ChatAPP.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatCont {

    private ChatMessageService chatMessageService;

    private SimpMessagingTemplate messagingTemplate;

    public ChatCont(ChatMessageService chatMessageService, SimpMessagingTemplate messagingTemplate) {
        this.chatMessageService = chatMessageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){

        ChatMessage message = chatMessageService.saveMessage(chatMessage);

        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(),
                "/queue/messages",
                ChatMessageNotification.builder()
                        .id(message.getChatId())
                        .senderId(message.getSenderId())
                        .receiverId(message.getReceiverId())
                        .content(message.getContent())
                        .build());
    }

    @GetMapping("/message/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findMessages(
            @PathVariable String senderId,
            @PathVariable String receiverId
    ){
       return ResponseEntity.ok(chatMessageService.getChatMessages(senderId,receiverId));
    }

}
