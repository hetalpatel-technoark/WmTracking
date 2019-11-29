/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.repositories.jobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class jobService {

    @Autowired
    jobRepository jobRepository;

    public void save(MaJobs maJobs) {
        jobRepository.save(maJobs);
    }

    public List<MaJobs> list(String satus) {
        return jobRepository.list(satus);
    }

    public MaJobs findone(String satus, Long id) {
        return jobRepository.findone(satus, id);
    }

    public Long count(String satus) {
        return jobRepository.count(satus);
    }

    public List<Object[]> findMonthWiseJob() {
        return jobRepository.findMonthWiseJob();
    }

    public List<Object[]> findDriverWiseJob() {
        return jobRepository.findDriverWiseJob();
    }

    public List<Object[]> findCustomerWiseJob() {
        return jobRepository.findCustomerWiseJob();
    }
}
