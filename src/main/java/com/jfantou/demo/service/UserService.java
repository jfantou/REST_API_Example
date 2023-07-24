package com.jfantou.demo.service;

import com.jfantou.demo.exception.UserNotFoundException;
import com.jfantou.demo.model.User;
import com.jfantou.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        User userSave = userRepository.save(user);
        return userSave;
    }

    public User getById(int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException(String.valueOf(id));
        return user.get();
    }

    public void deleteById(int id){
        userRepository.deleteById(id);
    }
}
