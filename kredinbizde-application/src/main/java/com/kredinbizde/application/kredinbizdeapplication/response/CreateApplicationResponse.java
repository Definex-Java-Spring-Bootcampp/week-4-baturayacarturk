package com.kredinbizde.application.kredinbizdeapplication.response;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateApplicationResponse {
    private String bankName;
    private ApplicationType applicationType;
    private Integer loanId;
    private Integer creditCardId;
    private ApplicationStatus applicationStatus;
    private LocalDateTime localDateTime;
    private String message;
}
