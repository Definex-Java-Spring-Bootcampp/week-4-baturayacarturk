package com.kredinbizde.application.kredinbizdeapplication.mapper;

import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.enums.ApplicationStatus;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.UpdateApplicationResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationConverter {

    public Application createApplicationRequestToApplication(CreateApplicationRequest createApplicationRequest){
        Application application = new Application();
        application.setApplicationStatus(ApplicationStatus.INITIAL);
        application.setBankId(createApplicationRequest.getBankId());
        application.setApplicationDate(LocalDateTime.now());
        application.setApplicationType(createApplicationRequest.getApplicationType());
        application.setEmail(createApplicationRequest.getEmail());
        application.setLoanId(
                createApplicationRequest.getLoanId() == null ? null: createApplicationRequest.getLoanId()
        );
        application.setCreditCardId(
                createApplicationRequest.getCreditCardId() == null ? null: createApplicationRequest.getCreditCardId()
        );
        application.setBankName(createApplicationRequest.getBankName());
        return application;
    }
    public CreateApplicationResponse applicationToCreateApplicationResponse(Application application){
        CreateApplicationResponse response = new CreateApplicationResponse();
        response.setApplicationStatus(ApplicationStatus.INITIAL);
        response.setApplicationType(application.getApplicationType());
        response.setMessage("Your application is succeded. You will be notified from your bank when update is performed");
        response.setLocalDateTime(application.getApplicationDate());
        if (application.getLoanId() == null) {
            response.setCreditCardId(application.getCreditCardId());
        } else {
            response.setLoanId(application.getLoanId());
        }
        response.setBankName(application.getBankName());
        return response;
    }
    public GetApplicationResponse mapApplicationToResponse(Application application) {
        GetApplicationResponse response = new GetApplicationResponse();
        response.setBankName(application.getBankName());
        response.setApplicationType(application.getApplicationType());
        response.setLoanId(application.getLoanId());
        response.setCreditCardId(application.getCreditCardId());
        response.setApplicationStatus(application.getApplicationStatus());
        response.setApplicationDate(application.getApplicationDate());
        return response;
    }
    public GetAllApplicationResponse mapApplicationsToResponse(List<Application> applications) {
        GetAllApplicationResponse response = new GetAllApplicationResponse();
        List<GetApplicationResponse> applicationResponses = applications.stream()
                .map(this::mapApplicationToResponse)
                .collect(Collectors.toList());
        response.setApplications(applicationResponses);
        return response;
    }
    public UpdateApplicationResponse mapApplicationToUpdateResponse(Application application) {
        UpdateApplicationResponse response = new UpdateApplicationResponse();
        response.setApplicationId(application.getId());
        response.setEmail(application.getEmail());
        response.setApplicationStatus(application.getApplicationStatus());
        response.setMessage("Your application update is successful. We will notice you the result");
        return response;
    }

}
