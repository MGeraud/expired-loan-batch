package com.geraud.expiredloanbatch.service;

import com.geraud.expiredloanbatch.dao.LoanDao;
import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanDao loanDao;

    @Override
    public List<Loan> listAllLoans(int refresh , LocalDate beforeDate) {
        return loanDao.findAllByBookBackDateIsNullAndRefreshEndingCounterEqualsAndStartingDateLessThan(refresh, beforeDate);
    }
}
