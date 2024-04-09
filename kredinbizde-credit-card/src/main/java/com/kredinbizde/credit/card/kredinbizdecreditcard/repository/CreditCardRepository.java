package com.kredinbizde.credit.card.kredinbizdecreditcard.repository;

import com.kredinbizde.credit.card.kredinbizdecreditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    CreditCard findByApplicationId(String applicationId);

    List<CreditCard> findByBankIdAndBankNameAndCreditCardIdAndUserEmail(
            Integer bankId,
            String bankName,
            Integer creditCardId,
            String userEmail
    );

}