package com.kredinbizde.application.kredinbizdeapplication.response;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationResponse {
    private String applicationId;
    private String email;
    private ApplicationStatus applicationStatus;
    private String message;
}
