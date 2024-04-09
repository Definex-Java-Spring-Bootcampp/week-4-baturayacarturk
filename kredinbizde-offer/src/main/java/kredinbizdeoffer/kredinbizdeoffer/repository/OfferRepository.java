package kredinbizdeoffer.kredinbizdeoffer.repository;

import feign.Param;
import kredinbizdeoffer.kredinbizdeoffer.entity.Offer;
import kredinbizdeoffer.kredinbizdeoffer.enums.CreditCardType;
import kredinbizdeoffer.kredinbizdeoffer.enums.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    @Query("SELECT o FROM Offer o WHERE " +
            "(:bankId IS NULL OR o.bankId = :bankId) AND " +
            "(:cardId IS NULL OR o.cardId = :cardId) AND " +
            "(:creditCardType IS NULL OR o.creditCardType = :creditCardType) AND " +
            "(:loanId IS NULL OR o.loanId = :loanId) AND " +
            "(:loanType IS NULL OR o.loanType = :loanType)")
    Optional<Offer> findByParams(
            @Param("bankId") Integer bankId,
            @Param("cardId") Integer cardId,
            @Param("creditCardType") CreditCardType creditCardType,
            @Param("loanId") Integer loanId,
            @Param("loanType") LoanType loanType
    );
    List<Offer> findByBankId(Integer bankId);
    List<Offer> findByCreditCardType(CreditCardType creditCardType);
    List<Offer> findByLoanType(LoanType loanType);

}