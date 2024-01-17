package com.panda.ChatAPP.repo;

import com.panda.ChatAPP.model.Status;
import com.panda.ChatAPP.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status online);
}
