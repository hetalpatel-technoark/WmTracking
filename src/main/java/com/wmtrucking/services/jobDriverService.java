/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobDriver;
import com.wmtrucking.repositories.jobDriverRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteOldDriverJob(String satus, Long jobId) {
        jobDriverRepository.deleteOldDriverJob(satus, jobId);
    }

    @Transactional
    public void delete(MaJobDriver maJobDriver){
    jobDriverRepository.delete(maJobDriver);
    }
    @Transactional
    public void delete(List<MaJobDriver> maJobDriver){
    jobDriverRepository.deleteInBatch(maJobDriver);
    }
    
     @Transactional
    public void deleteOldDriversJob(String satus, Long jobId, Long Driverid) {
        jobDriverRepository.deleteOldDriversJob(satus, jobId, Driverid);
    }

    public MaJobDriver findDriver(String satus, Long jobId, Long Driverid) {
        return jobDriverRepository.findDriver(satus, jobId, Driverid);
    }

    public List<MaJobDriver> listOfDriver(String satus, Long jobId) {
        return jobDriverRepository.listOfDriver(satus, jobId);
    }
}
