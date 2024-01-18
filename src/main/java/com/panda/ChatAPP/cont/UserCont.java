package com.panda.ChatAPP.cont;

import com.panda.ChatAPP.model.User;
import com.panda.ChatAPP.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserCont {
    private UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User saveUser(@Payload User user){

         userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User disConnect(@Payload User user){
        userService.userDisconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getConnectedUsers(){
        return ResponseEntity.ok(userService.findConnectedUser());
    }


}
