package com.kredinbizde.loan.kredinbizdeloan.service;

import com.kredinbizde.loan.kredinbizdeloan.entity.Loan;
import com.kredinbizde.loan.kredinbizdeloan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Integer id) {
        return loanRepository.findById(id);
    }
}