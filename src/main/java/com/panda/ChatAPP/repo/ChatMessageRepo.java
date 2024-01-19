package com.panda.ChatAPP.repo;

import com.panda.ChatAPP.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepo extends JpaRepository<ChatMessage,String> {
    List<ChatMessage> findByChatId(String s);
}
