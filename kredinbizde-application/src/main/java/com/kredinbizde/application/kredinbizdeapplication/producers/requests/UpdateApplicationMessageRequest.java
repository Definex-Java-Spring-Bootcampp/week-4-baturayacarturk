package com.kredinbizde.application.kredinbizdeapplication.producers.requests;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import lombok.Data;

@Data
public class UpdateApplicationMessageRequest {
    private String applicationId;
    private String email;
    private ApplicationStatus applicationStatus;
    private String operationType;
}
