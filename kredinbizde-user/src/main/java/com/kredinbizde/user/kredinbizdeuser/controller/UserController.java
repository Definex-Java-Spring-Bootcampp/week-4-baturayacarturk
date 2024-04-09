package com.kredinbizde.user.kredinbizdeuser.controller;

import com.kredinbizde.user.kredinbizdeuser.entity.User;
import com.kredinbizde.user.kredinbizdeuser.request.CreateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.request.UpdateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.response.CreateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.GetUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.UpdateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/get-user-by")
    public ResponseEntity<GetUserResponse> getUserByEmail(@RequestParam String email) {
        GetUserResponse userResponse = userService.getUserByEmail(email);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest user) {
        CreateUserResponse response = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserResponse response = userService.updateUser(updateUserRequest);
        return ResponseEntity.ok(response);
    }
}