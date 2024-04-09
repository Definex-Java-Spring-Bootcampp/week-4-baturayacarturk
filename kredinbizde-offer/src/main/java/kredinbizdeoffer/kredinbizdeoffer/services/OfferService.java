package kredinbizdeoffer.kredinbizdeoffer.services;

import kredinbizdeoffer.kredinbizdeoffer.entity.Offer;
import kredinbizdeoffer.kredinbizdeoffer.enums.CreditCardType;
import kredinbizdeoffer.kredinbizdeoffer.enums.LoanType;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    Optional<Offer> findByParams(Integer bankId, Integer cardId, CreditCardType creditCardType, Integer loanId, LoanType loanType);
    List<Offer> getAllOffers();
    List<Offer> getOffersByBankId(Integer bankId);
    List<Offer> getOffersByCreditCardType(CreditCardType creditCardType);
    List<Offer> getOffersByLoanType(LoanType loanType);
    Offer updateOffer(Integer id, Offer updatedOffer);
    Offer createOffer(Offer offer);
}
