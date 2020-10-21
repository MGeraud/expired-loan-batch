package com.geraud.expiredloanbatch.scheduler;

import com.geraud.expiredloanbatch.jobs.ExpiredLoanJob;
import com.geraud.expiredloanbatch.model.Loan;
import com.geraud.expiredloanbatch.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void listExpiredLoans() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        List<Loan> loans = loanService.listAllLoans();
        log.info("Chargement de la liste des prêt depuis la base de donnée effectué");
        JobExecution execution = jobLauncher.run(expiredLoanJob.sendMailToExpiredLoans(loans),new JobParameters());
    }

}
