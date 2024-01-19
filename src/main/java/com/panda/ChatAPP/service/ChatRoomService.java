package com.panda.ChatAPP.service;

import com.panda.ChatAPP.model.ChatRoom;
import com.panda.ChatAPP.repo.ChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private ChatRoomRepo chatRoomRepo;

    public ChatRoomService(ChatRoomRepo chatRoomRepo) {
        this.chatRoomRepo = chatRoomRepo;
    }

    public Optional<String> createChatRoomIfNotExist(String senderId,
                                                     String receiverId,
                                                     Boolean createChatRoomIfNotExist){

        return chatRoomRepo.findBySenderIdAndReceiverId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(() ->
                {
                    if (createChatRoomIfNotExist) {
                        var chatId = createChatRoom(senderId, receiverId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });
    }

    private String createChatRoom(String senderId, String receiverId) {

       var chatId=  String.format("%s_%s",senderId,receiverId);

        ChatRoom sender_receiver = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .receiverId(receiverId)
                .build();
        ChatRoom receiver_sender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(receiverId)
                .receiverId(senderId)
                .build();

        chatRoomRepo.save(sender_receiver);
        chatRoomRepo.save(receiver_sender);

        return chatId;

    }
}
