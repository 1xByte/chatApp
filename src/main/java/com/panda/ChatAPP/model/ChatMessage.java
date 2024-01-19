package com.panda.ChatAPP.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage {

    @Id
    private String chatMessageId;
    private String chatId;
    private String senderId;
    private String receiverId;
    private String content;
    private Date time;
}
