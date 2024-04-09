package com.kredinbizde.user.kredinbizdeuser.service;

import com.kredinbizde.user.kredinbizdeuser.entity.User;
import com.kredinbizde.user.kredinbizdeuser.request.CreateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.request.UpdateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.response.CreateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.GetUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.UpdateUserResponse;

import java.util.List;

public interface UserService {
    CreateUserResponse createUser(CreateUserRequest user);
     List<User> getAllUsers();
    GetUserResponse getUserByEmail(String email);
    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest);


}
