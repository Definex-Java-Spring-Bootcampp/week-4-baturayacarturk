package com.kredinbizde.application.kredinbizdeapplication.entity;

import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "applications")
@Data
public class Application {
    @Id
    private String id;
    private Integer bankId;
    private String bankName;
    private String email;
    private ApplicationStatus applicationStatus;
    private LocalDateTime applicationDate;
    private ApplicationType applicationType;
    private Integer loanId;
    private Integer creditCardId;


}
