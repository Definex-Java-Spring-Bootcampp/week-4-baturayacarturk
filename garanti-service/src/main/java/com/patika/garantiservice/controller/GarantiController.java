package com.patika.garantiservice.controller;

import com.patika.garantiservice.communication.OfferFeignClient;
import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.request.SendOfferToKredinBizden;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/garanti/v1/")
@RequiredArgsConstructor
public class GarantiController {
    @Autowired
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
