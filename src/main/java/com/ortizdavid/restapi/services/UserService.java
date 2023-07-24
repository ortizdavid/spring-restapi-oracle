package com.ortizdavid.restapi.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ortizdavid.restapi.entities.User;
import com.ortizdavid.restapi.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                            .orElseThrow();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User userDetails, Long id) {
        User user = this.getById(id);
        user.setUserName(userDetails.getUserName());
        user.setPassword(userDetails.getPassword());
        user.setActive(userDetails.getActive());
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = this.getById(id);
        userRepository.delete(user);
    }

    
}
