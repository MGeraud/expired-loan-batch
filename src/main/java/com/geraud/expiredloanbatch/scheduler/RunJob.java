package com.geraud.expiredloanbatch.scheduler;

import com.geraud.expiredloanbatch.jobs.ExpiredLoanJob;

import com.geraud.expiredloanbatch.model.Loan;
import com.geraud.expiredloanbatch.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe lancant le batch à période régulière.
 * Utilisation en paramètre de job de jobID basée sur l'heure du system pour pouvoir le relancer régulièrement
 */
@Component
@Slf4j
public class RunJob {
    @Autowired
    LoanService loanService;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    ExpiredLoanJob expiredLoanJob;

    @Scheduled(cron = "1 * * * * *")
    public void listExpiredLoans() throws Exception {
        List<Loan> loans = loanService.listAllLoans(0 , LocalDate.now().minusWeeks(4));
        List<Loan> loansAfterRefreshPeriod = loanService.listAllLoans(1,LocalDate.now().minusWeeks(8));
        log.info("Chargement de la liste des prêt depuis la base de donnée effectué");
        JobExecution execution = jobLauncher.run(expiredLoanJob.sendMailToExpiredLoans(loans, loansAfterRefreshPeriod), new JobParametersBuilder().addString("jobID", String.valueOf(System.currentTimeMillis())).toJobParameters());
    }
}
