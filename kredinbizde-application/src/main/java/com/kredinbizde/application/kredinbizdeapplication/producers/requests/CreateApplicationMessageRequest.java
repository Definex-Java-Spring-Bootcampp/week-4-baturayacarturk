package com.kredinbizde.application.kredinbizdeapplication.producers.requests;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import lombok.Data;
@Data
public class CreateApplicationMessageRequest {
    private String applicationId;
    private Integer bankId;
    private String email;
    private String bankName;
    private Integer loanId;
    private Integer creditCardId;
    private String operationType;
    private ApplicationStatus applicationStatus;
}
