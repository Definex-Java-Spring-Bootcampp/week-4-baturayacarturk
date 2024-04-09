package com.kredinbizde.credit.card.kredinbizdecreditcard.controller;

import com.kredinbizde.credit.card.kredinbizdecreditcard.entity.CreditCard;
import com.kredinbizde.credit.card.kredinbizdecreditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @GetMapping("/findByApplicationId")
    public CreditCard findByApplicationId(@RequestParam String applicationId) {
        return creditCardService.findByApplicationId(applicationId);
    }


    @GetMapping("/search")
    public List<CreditCard> searchCreditCards(
            @RequestParam(required = false) Integer bankId,
            @RequestParam(required = false) String bankName,
            @RequestParam(required = false) Integer creditCardId,
            @RequestParam(required = false) String userEmail
    ) {
        return creditCardService.searchCreditCards(bankId, bankName, creditCardId, userEmail);
    }
}