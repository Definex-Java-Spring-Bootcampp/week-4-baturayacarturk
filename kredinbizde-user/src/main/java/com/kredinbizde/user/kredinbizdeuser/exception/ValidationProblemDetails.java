package com.kredinbizde.user.kredinbizdeuser.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ValidationProblemDetails {
    private int status;
    private String type;
    private String title;
    private List<String> detail;
    private String instance;

}
