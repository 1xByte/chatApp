package com.panda.ChatAPP.repo;

import com.panda.ChatAPP.model.Status;
import com.panda.ChatAPP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
    List<User> findAllByStatus(Status online);
}
