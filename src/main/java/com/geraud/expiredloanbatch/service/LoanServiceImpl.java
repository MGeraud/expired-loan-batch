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

    private LocalDate beforeRefresh = LocalDate.now().minusWeeks(4);

    @Override
    public List<Loan> listAllLoans() {
        return loanDao.findAllByBookBackDateIsNullAndRefreshEndingCounterEqualsAndStartingDateLessThan(0, beforeRefresh);
    }
}
