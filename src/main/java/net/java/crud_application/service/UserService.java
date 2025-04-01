package net.java.crud_application.service;


import net.java.crud_application.model.User;
import net.java.crud_application.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    // Add User
    public User AddUser(User user){
        return userRepo.save(user);
    }
}
