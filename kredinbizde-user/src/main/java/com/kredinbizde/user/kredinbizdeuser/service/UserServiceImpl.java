package com.kredinbizde.user.kredinbizdeuser.service;

import com.kredinbizde.user.kredinbizdeuser.entity.User;
import com.kredinbizde.user.kredinbizdeuser.exception.BusinessException;
import com.kredinbizde.user.kredinbizdeuser.exception.ValidationException;
import com.kredinbizde.user.kredinbizdeuser.mapper.UserConverter;
import com.kredinbizde.user.kredinbizdeuser.repository.UserRepository;
import com.kredinbizde.user.kredinbizdeuser.request.CreateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.request.UpdateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.response.CreateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.GetUserResponse;
import com.kredinbizde.user.kredinbizdeuser.response.UpdateUserResponse;
import com.kredinbizde.user.kredinbizdeuser.validation.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RequestValidator requestValidator;
    private final UserConverter userConverter;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RequestValidator requestValidator, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.requestValidator = requestValidator;
        this.userConverter = userConverter;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public GetUserResponse getUserByEmail(String email) {
        if(email == null){
            throw new ValidationException("Email can not be empty");
        }
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new BusinessException("Please check your email");
        }
        return userConverter.createGetUserResponse(user.get());
    }

    public CreateUserResponse createUser(CreateUserRequest user) {
        requestValidator.validate(user);
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new BusinessException("User is exists with same mail address "+ user.getEmail());
        }
        User userToCreate = userConverter.createUserRequestToUser(user);

        userRepository.save(userToCreate);
        return userConverter.createUserResponse(userToCreate);
    }

    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        requestValidator.validate(updateUserRequest);
        Optional<User> user = userRepository.findByUserName(updateUserRequest.getUserName());
        if(user.isEmpty()){
            throw new BusinessException("User could not be found.");
        }
        compareFields(user.get(),updateUserRequest);
        userRepository.save(user.get());
        return userConverter.createUpdateUserRequest(user.get());

    }

    private void compareFields(User user, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.getPassword() != null && !updateUserRequest.getPassword().equals(user.getPassword())) {
            user.setPassword(updateUserRequest.getPassword());
        }
        if (updateUserRequest.getEmail() != null && !updateUserRequest.getEmail().equals(user.getEmail())) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getAddress() != null && !updateUserRequest.getAddress().equals(user.getAddress())) {
            user.setAddress(updateUserRequest.getAddress());
        }
    }
}
