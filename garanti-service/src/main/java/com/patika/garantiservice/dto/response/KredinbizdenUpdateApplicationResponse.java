package com.patika.garantiservice.dto.response;

import com.patika.garantiservice.enums.ApplicationStatus;
import lombok.Data;

@Data
public class KredinbizdenUpdateApplicationResponse {
    private String applicationId;
    private String email;
    private ApplicationStatus applicationStatus;
    private String operationType;
}
