package kredinbizdeoffer.kredinbizdeoffer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kredinbizdeoffer.kredinbizdeoffer.enums.CreditCardType;
import kredinbizdeoffer.kredinbizdeoffer.enums.LoanType;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
