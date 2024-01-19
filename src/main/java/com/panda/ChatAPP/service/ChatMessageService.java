package com.panda.ChatAPP.service;

import com.panda.ChatAPP.model.ChatMessage;
import com.panda.ChatAPP.repo.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatMessageService {
    private ChatMessageRepo chatMessageRepo;
    private ChatRoomService chatRoomService;

    public ChatMessageService(ChatMessageRepo chatMessageRepo, ChatRoomService chatRoomService) {
        this.chatMessageRepo = chatMessageRepo;
        this.chatRoomService = chatRoomService;
    }

    public ChatMessage saveMessage(ChatMessage chatMessage){

        var chatId = chatRoomService.createChatRoomIfNotExist(chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                true);

        chatMessage.setChatId(chatId.get());
        chatMessage.setChatMessageId(UUID.randomUUID().toString().substring(1,3));
        return chatMessageRepo.save(chatMessage);
    }

    public List<ChatMessage> getChatMessages(String senderId, String receiverId){

       var chatId= chatRoomService.createChatRoomIfNotExist(senderId,receiverId,false);

       return chatId.map(chatMessageRepo :: findByChatId).orElse(new ArrayList<>());
    }






}
