package com.patika.akbankservice.dto.response;

import com.patika.akbankservice.enums.ApplicationStatus;
import lombok.Data;

@Data
public class KredinbizdenUpdateApplicationResponse {
    private String applicationId;
    private String email;
    private ApplicationStatus applicationStatus;
    private String operationType;
}
