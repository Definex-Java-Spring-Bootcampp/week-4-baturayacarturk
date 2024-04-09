package com.kredinbizde.credit.card.kredinbizdecreditcard.service;

import com.kredinbizde.credit.card.kredinbizdecreditcard.entity.CreditCard;
import com.kredinbizde.credit.card.kredinbizdecreditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public CreditCard findByApplicationId(String applicationId) {
        return creditCardRepository.findByApplicationId(applicationId);
    }

    public List<CreditCard> searchCreditCards(
            Integer bankId,
            String bankName,
            Integer creditCardId,
            String userEmail
    ) {
        return creditCardRepository.findByBankIdAndBankNameAndCreditCardIdAndUserEmail(bankId, bankName, creditCardId, userEmail);
    }
}