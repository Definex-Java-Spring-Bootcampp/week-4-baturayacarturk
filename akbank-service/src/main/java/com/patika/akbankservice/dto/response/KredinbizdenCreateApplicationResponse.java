package com.patika.akbankservice.dto.response;

import com.patika.akbankservice.enums.ApplicationStatus;
import lombok.Data;

@Data
public class KredinbizdenCreateApplicationResponse {
    private String applicationId;
    private Integer bankId;
    private String email;
    private String bankName;
    private Integer loanId;
    private Integer creditCardId;
    private ApplicationStatus applicationStatus;
}
