package com.kredinbizde.user.kredinbizdeuser.mapper;

import com.kredinbizde.user.kredinbizdeuser.entity.User;
import com.kredinbizde.user.kredinbizdeuser.request.CreateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.response.CreateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.GetUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.UpdateUserResponse;
import org.springframework.stereotype.Component;


@Component

public class UserConverter {

    public User createUserRequestToUser(CreateUserRequest createUserRequest){
        User user = new User();
        user.setUserName(createUserRequest.getUserName());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setAddress(createUserRequest.getAddress());
        return user;
    }

    public CreateUserResponse createUserResponse(User userToCreate) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.setUserName(userToCreate.getUserName());
        createUserResponse.setAddress(userToCreate.getAddress());
        createUserResponse.setEmail(userToCreate.getEmail());
        createUserResponse.setMessage("User has been created successfully");
        return createUserResponse;
    }

    public GetUserResponse createGetUserResponse(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        return response;
    }
    public UpdateUserResponse createUpdateUserRequest(User user){
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setAddress(user.getAddress());
        updateUserResponse.setEmail(user.getEmail());
        updateUserResponse.setUserName(user.getUserName());
        return updateUserResponse;
    }
}
