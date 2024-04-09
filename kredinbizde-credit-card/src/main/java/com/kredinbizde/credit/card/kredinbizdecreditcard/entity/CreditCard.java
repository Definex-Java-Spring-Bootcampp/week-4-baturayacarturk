package com.kredinbizde.credit.card.kredinbizdecreditcard.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer bankId;
    private String bankName;
    private String userEmail;
    private String applicationId;
    private Integer creditCardId;
    private LocalDateTime applicationDate;
}
