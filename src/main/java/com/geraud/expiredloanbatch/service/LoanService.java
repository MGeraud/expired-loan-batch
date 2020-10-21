package com.geraud.expiredloanbatch.service;

import com.geraud.expiredloanbatch.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> listAllLoans();
}
