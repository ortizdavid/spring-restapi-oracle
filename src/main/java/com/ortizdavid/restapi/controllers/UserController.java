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

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.ortizdavid.restapi.entities.User;
import com.ortizdavid.restapi.services.UserService;
import com.ortizdavid.restapi.utils.Response;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Users", description = "Handle Users of application")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Operation(summary = "Retrieve all users", description = "If does not found records, return an error response.")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User>users = userService.getAll();
        if (users.size() == 0) {
            return Response.build("Users not found!", null, 0, HttpStatus.NOT_FOUND);
        } else {
            return Response.build("Users found successfully!", users, users.size(), HttpStatus.OK);
        }
    }

    @Operation(summary = "Retrieve an User by id", description = "If user doesn't exists, return error.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Response.build("User not found!", null, 0, HttpStatus.NOT_FOUND);
        } else {
            return Response.build("User found successfully!", user, 1, HttpStatus.OK);
        }
    }

    @Operation(summary = "Create an user", description = "Create an user passing all user data.")
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User userBody) {
        User user = userService.create(userBody);
        if (user == null) {
            return Response.build("Error while creating user!", null, 0, HttpStatus.BAD_REQUEST);
        } else {
            return Response.build("User created successfully!", user, 1, HttpStatus.CREATED);
        }
    }

    @Operation(summary = "Update an user by id", description = "Update an user passing all user data.")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User userBody, @PathVariable Long id) {
        User user = userService.update(userBody, id);
        if (user == null) {
            return Response.build("Error while updating user!", null, 0, HttpStatus.BAD_REQUEST);
        } else {
            return Response.build("User updated successfully!", user, 1, HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete an User by id", description = "If user doesn't exists, return error.")
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
