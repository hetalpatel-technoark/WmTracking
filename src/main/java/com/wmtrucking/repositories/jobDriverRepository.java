/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaJobDriver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface jobDriverRepository extends JpaRepository<MaJobDriver, Long> {

    @Query(nativeQuery = true, value = "select string_agg(CAST(u.driver_id as varchar), ',') from ma_job_driver u where u.job_id=?2 and "
            + "u.job_id in(select id from ma_jobs where status=?1 ) ")
    String list(String satus, Long jobId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from ma_job_driver  where job_id=?2 and job_id in(select id from ma_jobs where status=?1 ) ")
    void deleteOldDriverJob(String satus, Long jobId);

  
    @Query(nativeQuery = true, value = "select d.* from ma_job_driver d where d.id=?1 and d.driver_id not in(select driverid from ma_job_transaction "
            + " where job_id=d.job_id)")
    MaJobDriver driverJob(Long Id);

    @Modifying
    @Query(nativeQuery = true, value = "delete from ma_job_driver  where job_id=?2 and driver_id=?3 and "
            + "job_id in(select id from ma_jobs where status=?1 ) ")
    void deleteOldDriversJob(String satus, Long jobId, Long Driverid);

    @Query(nativeQuery = true, value = "select * from ma_job_driver u where u.job_id=?2 and u.job_id in(select id from ma_jobs where status=?1 ) ")
    List<MaJobDriver> listOfDriver(String satus, Long jobId);

//    @Query(nativeQuery = true, value = "select * from ma_job_driver u where u.job_id=?2 and driver_id=?3 and "
//            + " u.job_id in(select id from ma_jobs where status=?1 ) ")
//    MaJobDriver findDriver(String satus, Long jobId, Long Driverid);
}
