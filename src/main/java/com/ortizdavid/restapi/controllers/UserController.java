package com.ortizdavid.restapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ortizdavid.restapi.entities.User;
import com.ortizdavid.restapi.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User>users = userService.getAll();
        Map<String, Object> response = new HashMap<String, Object>();
        if (users.size() == 0) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "No users found!");
            response.put("count", 0);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", HttpStatus.OK.value());
            response.put("message", "Users Found successfully!");
            response.put("count", users.size());
            response.put("data", users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        Map<String, Object> response = new HashMap<String, Object>();
        if (user == null) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "User not found!");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("status", HttpStatus.OK.value());
            response.put("message", "User found successfully!");
            response.put("data", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User userBody) {
        Map<String, Object> response = new HashMap<String, Object>();
        User user = userService.create(userBody);
        if (user == null) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Error while creating user!");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "User created successfully!");
            response.put("data", user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User userBody, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<String, Object>();
        User user = userService.update(userBody, id);
        if (user == null) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("message", "Error while updating user!");
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.put("status", HttpStatus.CREATED.value());
            response.put("message", "User updated successfully!");
            response.put("data", user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<String, Object>();
        userService.delete(id);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "User deleted successfully!");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
