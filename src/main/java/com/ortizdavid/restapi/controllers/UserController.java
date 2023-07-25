package com.ortizdavid.restapi.controllers;

import java.util.List;

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
import com.ortizdavid.restapi.utils.Response;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User>users = userService.getAll();
        if (users.size() == 0) {
            return Response.build("Users not found!", null, 0, HttpStatus.NOT_FOUND);
        } else {
            return Response.build("Users found successfully!", users, users.size(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Response.build("User not found!", null, 0, HttpStatus.NOT_FOUND);
        } else {
            return Response.build("User found successfully!", user, 1, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User userBody) {
        User user = userService.create(userBody);
        if (user == null) {
            return Response.build("Error while creating user!", null, 0, HttpStatus.BAD_REQUEST);
        } else {
            return Response.build("User created successfully!", user, 1, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User userBody, @PathVariable Long id) {
        User user = userService.update(userBody, id);
        if (user == null) {
            return Response.build("Error while updating user!", null, 0, HttpStatus.BAD_REQUEST);
        } else {
            return Response.build("User updated successfully!", user, 1, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Response.build("Error while deleting user!", null, 0, HttpStatus.BAD_REQUEST);
        } else {
            userService.delete(id);
            return Response.build("User deleted successfully!", null, 1, HttpStatus.OK);
        }
    }

}
