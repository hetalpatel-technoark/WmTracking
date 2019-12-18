/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobTransaction;
import com.wmtrucking.repositories.JobTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Scope(value = "request")
@Service
public class JobTransactionService {

    @Autowired
    JobTransactionRepository jobTransactionRepository;

    public void save(MaJobTransaction maJobTransaction) {
        jobTransactionRepository.save(maJobTransaction);
    }

    public Long totalJobTransactionCount(Long jobid) {
        return jobTransactionRepository.totalJobTransactionCount(jobid);
    }
}
