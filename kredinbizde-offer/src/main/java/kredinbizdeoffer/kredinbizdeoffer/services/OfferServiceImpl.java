package kredinbizdeoffer.kredinbizdeoffer.services;

import kredinbizdeoffer.kredinbizdeoffer.entity.Offer;
import kredinbizdeoffer.kredinbizdeoffer.enums.CreditCardType;
import kredinbizdeoffer.kredinbizdeoffer.enums.LoanType;
import kredinbizdeoffer.kredinbizdeoffer.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Optional<Offer> findByParams(Integer bankId, Integer cardId, CreditCardType creditCardType, Integer loanId, LoanType loanType) {
        return offerRepository.findByParams(bankId, cardId, creditCardType, loanId, loanType);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> getOffersByBankId(Integer bankId) {
        return offerRepository.findByBankId(bankId);
    }

    @Override
    public List<Offer> getOffersByCreditCardType(CreditCardType creditCardType) {
        return offerRepository.findByCreditCardType(creditCardType);
    }

    @Override
    public List<Offer> getOffersByLoanType(LoanType loanType) {
        return offerRepository.findByLoanType(loanType);
    }
    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Integer id, Offer updatedOffer) {
        Offer existingOffer = offerRepository.findById(id).orElse(null);
        if (existingOffer != null) {
            updatedOffer.setId(id);
            return offerRepository.save(updatedOffer);
        }
        return null;
    }
}
