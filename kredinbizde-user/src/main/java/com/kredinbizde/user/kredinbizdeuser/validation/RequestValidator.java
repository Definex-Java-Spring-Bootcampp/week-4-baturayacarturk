package com.kredinbizde.user.kredinbizdeuser.validation;

import com.kredinbizde.user.kredinbizdeuser.exception.ValidationException;
import com.kredinbizde.user.kredinbizdeuser.request.CreateUserRequest;
import com.kredinbizde.user.kredinbizdeuser.request.Request;
import com.kredinbizde.user.kredinbizdeuser.request.UpdateUserRequest;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class RequestValidator implements IValidator {
    @Override
    public void validate(Request target) {
        if (target instanceof CreateUserRequest) {
            validateCreateUserRequest((CreateUserRequest) target);
        }
        else if(target instanceof UpdateUserRequest){
            validateUpdateUserRequest((UpdateUserRequest) target );
        }

    }
    private void validateCreateUserRequest(CreateUserRequest request){
        var errors = new ArrayList<String>();
        if (request.getAddress() == null) {
            errors.add("Address must not be empty");
        }
        if (request.getUserName() == null) {
            errors.add("User name must not be empty");
        }
        if (request.getPassword() == null) {
            errors.add("Password must not be empty");
        }
        if(request.getEmail() == null){
            errors.add("Email must not be empty");
        }
        if(!errors.isEmpty()){
            throw new ValidationException("Validation is failed ",errors);
        }
    }
    private void validateUpdateUserRequest (UpdateUserRequest request){
        var errors = new ArrayList<String>();
        if (request.getUserName() == null) {
            errors.add("User name must not be empty");
        }
        if(!errors.isEmpty()){
            throw new ValidationException("Validation is failed ",errors);
        }
    }
}

