package com.kredinbizde.application.kredinbizdeapplication;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.kredinbizde.application.kredinbizdeapplication.controller.ApplicationController;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.service.ApplicationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private ApplicationController applicationController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateApplication() {
        CreateApplicationRequest request = new CreateApplicationRequest();
        CreateApplicationResponse mockResponse = new CreateApplicationResponse();

        when(applicationService.createApplication(request)).thenReturn(mockResponse);

        // When
        ResponseEntity<CreateApplicationResponse> responseEntity = applicationController.createApplication(request);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
        verify(applicationService).createApplication(request);
    }

    @Test
    public void testGetApplicationById() {
        // Given
        String id = "123";
        GetApplicationResponse mockResponse = new GetApplicationResponse();

        when(applicationService.getApplicationById(id)).thenReturn(mockResponse);

        // When
        ResponseEntity<GetApplicationResponse> responseEntity = applicationController.getApplicationById(id);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
        verify(applicationService).getApplicationById(id);
    }

    @Test
    public void testGetAllApplications() {
        //
        GetAllApplicationResponse mockResponse = new GetAllApplicationResponse();

        when(applicationService.getAllApplications()).thenReturn(mockResponse);

        // When
        ResponseEntity<GetAllApplicationResponse> responseEntity = applicationController.getAllApplications();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
        verify(applicationService).getAllApplications();
    }
}
