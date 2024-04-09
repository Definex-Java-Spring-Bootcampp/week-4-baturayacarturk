package com.patika.garantiservice.dto.response;

import com.patika.garantiservice.enums.ApplicationStatus;
import lombok.Data;

@Data
public class KredinbizdenApplicationResponse {
    private String applicationId;
    private Integer bankId;
    private String email;
    private String bankName;
    private Integer loanId;
    private Integer creditCardId;
    private ApplicationStatus applicationStatus;
}
