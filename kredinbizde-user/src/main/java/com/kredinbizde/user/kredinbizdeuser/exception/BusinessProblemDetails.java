package com.kredinbizde.user.kredinbizdeuser.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class BusinessProblemDetails {
    private int status;
    private String type;
    private String title;
    private String detail;
    private String instance;

}
