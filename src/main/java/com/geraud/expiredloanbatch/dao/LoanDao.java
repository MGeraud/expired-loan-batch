package com.geraud.expiredloanbatch.dao;

import com.geraud.expiredloanbatch.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long> {
    List<Loan> findAllByBookBackDateIsNullAndRefreshEndingCounterEqualsAndStartingDateLessThan(int refresh, LocalDate avantdate);
}
