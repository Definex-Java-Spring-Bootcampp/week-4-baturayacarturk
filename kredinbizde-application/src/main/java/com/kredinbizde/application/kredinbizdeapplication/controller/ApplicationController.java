package com.kredinbizde.application.kredinbizdeapplication.controller;

import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.UpdateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.UpdateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @PostMapping
    public ResponseEntity<CreateApplicationResponse> createApplication(@RequestBody CreateApplicationRequest application) {
        CreateApplicationResponse createdApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetApplicationResponse> getApplicationById(@PathVariable String id) {
        GetApplicationResponse application = applicationService.getApplicationById(id);
        return new ResponseEntity<>(application, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<GetAllApplicationResponse> getAllApplications() {
        GetAllApplicationResponse applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PutMapping("/updateApplication")
    public ResponseEntity<UpdateApplicationResponse> updateApplication(@RequestBody UpdateApplicationRequest application) {
        UpdateApplicationResponse updatedApplication = applicationService.updateApplication(application);
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);

    }
}
