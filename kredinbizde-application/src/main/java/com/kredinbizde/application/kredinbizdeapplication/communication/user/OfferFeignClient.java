package com.kredinbizde.application.kredinbizdeapplication.communication.user;

import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetOfferResponse;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.enums.CreditCardType;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.enums.LoanType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name="kredinbizde-offer",url = "${offer-service.url}")

public interface OfferFeignClient {
    @GetMapping("/getOfferByParams")
    Optional<GetOfferResponse> findByParams(
            @RequestParam(required = false) Integer bankId,
            @RequestParam(required = false) Integer cardId,
            @RequestParam(required = false) CreditCardType creditCardType,
            @RequestParam(required = false) Integer loanId,
            @RequestParam(required = false) LoanType loanType
    );
}
