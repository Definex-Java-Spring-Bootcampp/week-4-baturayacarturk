package com.kredinbizde.loan.kredinbizdeloan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bankId;
    private String bankName;
    private String userEmail;
    private String applicationId;
    private Integer loanId;
    private LocalDateTime applicationDate;
}
