package com.kredinbizde.application.kredinbizdeapplication.request;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationRequest implements Request{
    private String applicationId;
    private String email;
    private ApplicationStatus applicationStatus;
}
