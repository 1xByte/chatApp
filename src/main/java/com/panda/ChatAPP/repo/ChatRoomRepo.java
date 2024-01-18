package com.panda.ChatAPP.repo;

import com.panda.ChatAPP.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepo extends MongoRepository<ChatRoom,String> {
}
