package com.patika.garantiservice.communication;

import com.patika.garantiservice.dto.request.SendOfferToKredinBizden;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name="kredinbizde-offer",url = "${offer-service.url}")
public interface OfferFeignClient {
    @PostMapping("/createOffer")
    ResponseEntity<SendOfferToKredinBizden> createOfferToKredinBizden(@RequestBody SendOfferToKredinBizden offer);
}


