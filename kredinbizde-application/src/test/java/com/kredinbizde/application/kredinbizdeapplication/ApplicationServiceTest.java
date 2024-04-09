package com.kredinbizde.application.kredinbizdeapplication;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.OfferFeignClient;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.UserFeignClient;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetOfferResponse;
import com.kredinbizde.application.kredinbizdeapplication.communication.user.response.GetUserResponse;
import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.exception.BusinessException;
import com.kredinbizde.application.kredinbizdeapplication.exception.ValidationException;
import com.kredinbizde.application.kredinbizdeapplication.mapper.ApplicationConverter;
import com.kredinbizde.application.kredinbizdeapplication.mapper.BankNameConverter;
import com.kredinbizde.application.kredinbizdeapplication.producers.RabbitMQProducer;
import com.kredinbizde.application.kredinbizdeapplication.repository.ApplicationRepository;
import com.kredinbizde.application.kredinbizdeapplication.request.CreateApplicationRequest;
import com.kredinbizde.application.kredinbizdeapplication.response.CreateApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetAllApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.response.GetApplicationResponse;
import com.kredinbizde.application.kredinbizdeapplication.service.ApplicationServiceImpl;
import com.kredinbizde.application.kredinbizdeapplication.validation.RequestValidator;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {
    @InjectMocks
    private ApplicationServiceImpl applicationService;

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private UserFeignClient userFeignClient;

    @Mock
    private OfferFeignClient offerFeignClient;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private ApplicationConverter applicationConverter;

    @Mock
    private BankNameConverter bankNameConverter;

    @Mock
    private RabbitMQProducer producer;

    @Test
    public void givenValidUserAndOffer_whenCreateApplication_thenSuccess() {
        MockitoAnnotations.openMocks(this);

        GetUserResponse userResponse = new GetUserResponse();
        userResponse.setEmail("baturay");
        when(userFeignClient.getUserByEmail(anyString())).thenReturn(Optional.of(userResponse));
        when(offerFeignClient.findByParams(any(), any(), any(), any(), any())).thenReturn(Optional.of(new GetOfferResponse()));
        when(applicationRepository.findByEmailAndCreditCardIdOrEmailAndLoanId(any(), any(), any(), any())).thenReturn(new ArrayList<>());
        when(applicationRepository.save(any())).thenReturn(new Application());
        when(bankNameConverter.determineRoutingKey("akbank")).thenReturn("routing_key");
        when(applicationConverter.applicationToCreateApplicationResponse(any())).thenReturn(new CreateApplicationResponse());

        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setEmail("baturay");
        request.setBankName("akbank");
        CreateApplicationResponse response = applicationService.createApplication(request);
        assertNotNull(response);
    }
    @Test
    public void givenEmptyUser_whenCreateApplication_thenThrowBusinessException() {
        MockitoAnnotations.openMocks(this);
        when(userFeignClient.getUserByEmail(anyString())).thenReturn(Optional.empty());

        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setEmail("noemail@example.com");
        assertThrows(BusinessException.class, () -> applicationService.createApplication(request));
    }
    @Test
    public void givenEmptyOffer_whenCreateApplication_thenThrowBusinessException() {
        MockitoAnnotations.openMocks(this);
        GetUserResponse userResponse = new GetUserResponse();
        userResponse.setEmail("baturay");
        when(userFeignClient.getUserByEmail(anyString())).thenReturn(Optional.of(userResponse));
        when(offerFeignClient.findByParams(any(), any(), any(), any(), any())).thenReturn(Optional.empty());
        CreateApplicationRequest request = new CreateApplicationRequest();
        request.setEmail("baturay");
        assertThrows(BusinessException.class, () -> applicationService.createApplication(request));
    }
    @Test
    public void givenNullId_whenGetApplicationById_thenThrowValidationException() {
        MockitoAnnotations.openMocks(this);
        assertThrows(ValidationException.class, () -> applicationService.getApplicationById(null));
    }
    @Test
    public void givenNonexistId_whenGetApplicationById_thenThrowBusinessException() {

        MockitoAnnotations.openMocks(this);
        String nonExistentId = "non_existent_id";
        when(applicationRepository.findById(anyString())).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> applicationService.getApplicationById(nonExistentId));
        assertEquals(nonExistentId + " id is not exists", exception.getMessage());
    }
    @Test
    public void givenId_whenGetApplicationById_thenReturnGetApplicationResponse() {

        MockitoAnnotations.openMocks(this);
        String existingId = "existing_id";
        Application existingApplication = new Application();
        existingApplication.setId(existingId);
        when(applicationRepository.findById(anyString())).thenReturn(Optional.of(existingApplication));

        GetApplicationResponse expectedResponse = new GetApplicationResponse();

        when(applicationConverter.mapApplicationToResponse(existingApplication)).thenReturn(expectedResponse);

        GetApplicationResponse actualResponse = applicationService.getApplicationById(existingId);

        assertEquals(expectedResponse, actualResponse);
    }
    @Test
    public void testGetAllApplications_Success() {
        MockitoAnnotations.openMocks(this);
        List<Application> mockApplications = Arrays.asList(
                new Application(),
                new Application()
        );
        GetAllApplicationResponse mockResponse = new GetAllApplicationResponse();

        when(applicationRepository.findAll()).thenReturn(mockApplications);
        when(applicationConverter.mapApplicationsToResponse(mockApplications)).thenReturn(mockResponse);
        GetAllApplicationResponse response = applicationService.getAllApplications();
        assertEquals(mockResponse, response);
        verify(applicationRepository).findAll();
        verify(applicationConverter).mapApplicationsToResponse(mockApplications);
    }

}
