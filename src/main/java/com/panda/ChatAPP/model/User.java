package com.panda.ChatAPP.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    private String userId;
    private String nickName;

    private String fullName;

    @Enumerated
    private Status status;
}
