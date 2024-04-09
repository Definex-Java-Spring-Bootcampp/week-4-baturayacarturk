package com.kredinbizde.application.kredinbizdeapplication.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalServerExceptionDetails {
    private int status;
    private String type;
    private String title;
    private String detail;
    private String instance;
}
