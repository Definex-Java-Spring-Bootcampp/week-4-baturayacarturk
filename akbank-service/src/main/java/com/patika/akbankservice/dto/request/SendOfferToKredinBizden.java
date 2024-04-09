package com.patika.akbankservice.dto.request;


import com.patika.akbankservice.dto.request.enums.CreditCardType;
import com.patika.akbankservice.dto.request.enums.LoanType;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SendOfferToKredinBizden {
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
