package com.kredinbizde.loan.kredinbizdeloan.repository;

import com.kredinbizde.loan.kredinbizdeloan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

}