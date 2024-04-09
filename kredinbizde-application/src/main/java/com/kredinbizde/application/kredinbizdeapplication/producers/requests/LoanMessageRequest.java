package com.kredinbizde.application.kredinbizdeapplication.producers.requests;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class LoanMessageRequest {
    private Integer bankId;
    private String bankName;
    private String userEmail;
    private String applicationId;
    private Integer loanId;
    private LocalDateTime applicationDate;
}
