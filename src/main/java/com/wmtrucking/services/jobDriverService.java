/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobDriver;
import com.wmtrucking.pojo.DriverPojo;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.repositories.jobDriverRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(MaJobDriver maJobDriver) {
        jobDriverRepository.save(maJobDriver);
    }

    public void delete(MaJobDriver maJobDriver) {
        jobDriverRepository.delete(maJobDriver);
    }

    public String list(String satus, Long jobId) {
        return jobDriverRepository.list(satus, jobId);
    }

    @Transactional
    public void deleteOldDriverJob(String satus, Long jobId) {
        jobDriverRepository.deleteOldDriverJob(satus, jobId);
    }

    public MaJobDriver driverJob(Long Id) {
        return jobDriverRepository.driverJob(Id);
    }

//    @Transactional
//    public void delete(MaJobDriver maJobDriver) {
//        jobDriverRepository.delete(maJobDriver);
//    }
//    @Transactional
//    public void delete(List<MaJobDriver> maJobDriver) {
//        jobDriverRepository.deleteInBatch(maJobDriver);
//    }
//    @Transactional
//    public void deleteOldDriversJob(String satus, Long jobId, Long Driverid) {
//        jobDriverRepository.deleteOldDriversJob(satus, jobId, Driverid);
//    }
//    public MaJobDriver findDriver(String satus, Long jobId, Long Driverid) {
//        return jobDriverRepository.findDriver(satus, jobId, Driverid);
//    }
    public List<MaJobDriver> listOfDriver(String satus, Long jobId) {
        return jobDriverRepository.listOfDriver(satus, jobId);
    }

    public List<DriverPojo> getDriverList(Long jobid) {
        String query = "select	j.id,j.job_id,(SELECT TO_CHAR(j.createddate, 'Month DD, YYYY') as createddate),(select firstname from ma_driver where id=j.driver_id),"
                + "(select count(id) from ma_job_transaction where job_id=j.job_id and driverid=j.driver_id and status='Ended')as  donejobcount,"
                + "(select count(id) from ma_job_transaction where job_id=j.job_id and driverid=j.driver_id and status='Started')as  pickupjobcount           "
                + "                                	from ma_job_driver j where j.job_id=? ORDER BY j.id desc";
        List<DriverPojo> driverpojo = jdbcTemplate.query(query, new Object[]{jobid}, new BeanPropertyRowMapper<DriverPojo>(DriverPojo.class));
        return driverpojo;
    }
}
