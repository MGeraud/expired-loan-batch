package com.geraud.expiredloanbatch.service;

import com.geraud.expiredloanbatch.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {
    List<Loan> listAllLoans(int refresh , LocalDate beforeDate);
}
