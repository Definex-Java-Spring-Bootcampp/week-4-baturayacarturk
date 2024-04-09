package com.kredinbizde.application.kredinbizdeapplication.repository;

import com.kredinbizde.application.kredinbizdeapplication.entity.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application,String> {
    List<Application> findByEmailAndCreditCardIdOrEmailAndLoanId(String email, Integer creditCardId, String email1, Integer loanId);
}
