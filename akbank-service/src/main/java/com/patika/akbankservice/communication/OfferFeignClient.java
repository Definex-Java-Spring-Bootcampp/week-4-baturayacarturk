package com.patika.akbankservice.communication;

import com.patika.akbankservice.dto.request.SendOfferToKredinBizden;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="kredinbizde-offer",url = "${offer-service.url}")
public interface OfferFeignClient {
    @PostMapping("/createOffer")
    ResponseEntity<SendOfferToKredinBizden> createOfferToKredinBizden(@RequestBody SendOfferToKredinBizden offer);
}