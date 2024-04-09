package com.patika.akbankservice.controller;

import com.patika.akbankservice.communication.OfferFeignClient;
import com.patika.akbankservice.dto.request.ApplicationRequest;
import com.patika.akbankservice.dto.request.SendOfferToKredinBizden;
import com.patika.akbankservice.dto.response.ApplicationResponse;
import com.patika.akbankservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/akbank/v1/")
@RequiredArgsConstructor
public class AkbankController {

    private final ApplicationService applicationService;
    @Autowired
    private final OfferFeignClient offerFeignClient;
    @PostMapping
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
        return applicationService.createApplication(request);
    }
    @PostMapping("send-offer-to-kredin-bizden")
    public ResponseEntity<SendOfferToKredinBizden> sendOfferToKredinBizden(@RequestBody SendOfferToKredinBizden request) {
        request.setOfferDate(LocalDateTime.now());
        return offerFeignClient.createOfferToKredinBizden(request);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAll() {
        return ResponseEntity.ok(applicationService.getAll());
    }

}
