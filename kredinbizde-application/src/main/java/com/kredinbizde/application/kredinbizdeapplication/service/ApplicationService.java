package com.kredinbizde.application.kredinbizdeapplication.service;


import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.request.UpdateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.UpdateApplicationResponse;



public interface ApplicationService {
    CreateApplicationResponse createApplication(CreateApplicationRequest createApplicationRequest);
    GetApplicationResponse getApplicationById(String getApplicationByApplicationId);
    GetAllApplicationResponse getAllApplications();
    UpdateApplicationResponse updateApplication(UpdateApplicationRequest updateApplicationRequest);

}
