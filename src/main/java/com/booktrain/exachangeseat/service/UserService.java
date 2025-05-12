package com.booktrain.exachangeseat.service;

import com.booktrain.exachangeseat.dto.UserDTO;
import com.booktrain.exachangeseat.entity.User;
import com.booktrain.exachangeseat.exception.ResourceNotFoundException;
import com.booktrain.exachangeseat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmailId(userDTO.getEmailId());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());

        return userRepository.save(user);
    }

    public User getUserById(Long userId){
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()){
            return userById.get();
        }
        else{
            throw new ResourceNotFoundException("User is not available with userId "+userId);
        }
    }

    public User updateUser(User newUser,Long userId){
        User  user= this.getUserById(userId);
        user.setName(newUser.getName());
        user.setMobileNumber(newUser.getMobileNumber());
        user.setEmailId(newUser.getEmailId());
        user.setAge(newUser.getAge());
        user.setGender(newUser.getGender());

        return userRepository.save(user);
    }

    public String deleteUser(Long userId){
        this.getUserById(userId);
        userRepository.deleteById(userId);
        return "User is successfully deleted with userId "+userId;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
