/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobDriver;
import com.wmtrucking.repositories.jobDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class jobDriverService {

    @Autowired
    jobDriverRepository jobDriverRepository;

    public void save(MaJobDriver maJobDriver) {
        jobDriverRepository.save(maJobDriver);
    }

    public String list(String satus, Long jobId) {
        return jobDriverRepository.list(satus, jobId);
    }

    public String deleteOldDriverJob(String satus, Long jobId) {
        return jobDriverRepository.deleteOldDriverJob(satus, jobId);
    }
}
