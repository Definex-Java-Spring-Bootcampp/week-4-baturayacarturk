package com.kredinbizde.application.kredinbizdeapplication.communication.user.response;

import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.enums.CreditCardType;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.enums.LoanType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GetOfferResponse {
    private Integer bankId;
    private String bankName;
    private Integer cardId;
    private String cardName;
    private String cardDetails;
    private CreditCardType creditCardType;
    private Integer loanId;
    private LoanType loanType;
    private String loanDetails;
    private Integer interestRate;
    private Integer amount;
    private LocalDateTime offerDate;
}
