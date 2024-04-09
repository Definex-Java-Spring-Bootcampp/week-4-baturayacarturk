package com.kredinbizde.application.kredinbizdeapplication.validation;

import com.kredinbizde.application.kredinbizdeapplication.exception.ValidationException;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.Request;
import com.kredinbizde.application.kredinbizdeapplication.request.UpdateApplicationRequest;
import org.springframework.stereotype.Component;


import java.util.ArrayList;

@Component
public class RequestValidator implements IValidator {
    @Override
    public void validate(Request target) {
        if (target instanceof CreateApplicationRequest) {
            validateCreateApplicationRequest((CreateApplicationRequest) target);
        }
        else if (target instanceof UpdateApplicationRequest){
            validateUpdateApplicationRequest((UpdateApplicationRequest)target);
        }
    }
    private void validateCreateApplicationRequest(CreateApplicationRequest request) {
        var errors = new ArrayList<String>();
        if (request.getBankId() == null) {
            errors.add("Bank id must not be null");
        }
        if (request.getEmail() == null) {
            errors.add("User email must not be null");
        }
        if (request.getApplicationType() == null) {
            errors.add("Application type must not be null");
        }
        if(!errors.isEmpty()){
            throw new ValidationException("Validation is failed",errors);
        }
    }
    private void validateUpdateApplicationRequest(UpdateApplicationRequest target){
        var errors = new ArrayList<String>();

        if (target.getApplicationId() == null) {
            errors.add("Application id cannot be empty");
        }
        if (target.getEmail() == null) {
            errors.add("User email cannot be empty");
        }
        if(!errors.isEmpty()){
            throw new ValidationException("Validation is failed due to",errors);
        }
    }

}
