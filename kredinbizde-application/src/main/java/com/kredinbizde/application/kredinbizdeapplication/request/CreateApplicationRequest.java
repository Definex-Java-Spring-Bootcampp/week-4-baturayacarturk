package com.kredinbizde.application.kredinbizdeapplication.request;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationType;
import lombok.Data;
@Data
public class CreateApplicationRequest implements Request{
    private Integer bankId;
    private String email;
    private String bankName;
    private ApplicationType applicationType;
    private Integer loanId;
    private Integer creditCardId;

}
