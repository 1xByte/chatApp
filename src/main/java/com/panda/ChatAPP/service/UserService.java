package com.panda.ChatAPP.service;

import com.panda.ChatAPP.model.Status;
import com.panda.ChatAPP.model.User;
import com.panda.ChatAPP.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepo userRepo;

        public void saveUser(User user){

            user.setStatus(Status.ONLINE);
            userRepo.save(user);
        }

        public void userDisconnect(User user){

            var u =userRepo.findById(user.getNickName()).orElse(null);
            if(null != u){
                u.setStatus(Status.OFFLINE);
                userRepo.save(u);

            }

        }

        public List<User> findConnectedUser(){

            return  userRepo.findAllByStatus(Status.ONLINE);
        }

}
