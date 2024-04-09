package com.kredinbizde.application.kredinbizdeapplication;

import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import com.kredinbizde.application.kredinbizdeapplication.repository.ApplicationRepository;
import com.kredinbizde.application.kredinbizdeapplication.service.ApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

@DataMongoTest
@ExtendWith(MockitoExtension.class)
public class ApplicationRepositoryTest {
    @Mock
    private ApplicationRepository applicationRepository;

    @Test
    public void testFindByEmailAndCreditCardIdOrEmailAndLoanId() {
        Application application1 = new Application();
        application1.setId("1");
        application1.setEmail("test@example.com");
        application1.setCreditCardId(123);
        application1.setLoanId(null);

        Application application2 = new Application();
        application2.setId("2");
        application2.setEmail("test@example.com");
        application2.setCreditCardId(null);
        application2.setLoanId(456);

        List<Application> expectedResult = Arrays.asList(application1, application2);

        when(applicationRepository.findByEmailAndCreditCardIdOrEmailAndLoanId(any(), any(), any(), any()))
                .thenReturn(expectedResult);

        List<Application> actualResult = applicationRepository.findByEmailAndCreditCardIdOrEmailAndLoanId("test@example.com", 123, "test@example.com", 456);

        assertEquals(expectedResult, actualResult);
    }
}