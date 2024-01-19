package com.panda.ChatAPP.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageNotification {

    private String id;
    private String senderId;
    private String receiverId;
    private String content;
}
