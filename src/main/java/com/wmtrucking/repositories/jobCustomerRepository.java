/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaJobCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface jobCustomerRepository extends JpaRepository<MaJobCustomer, Long> {

  //  @Query(nativeQuery = true, value = "select string_agg(CAST(u.driver_id as varchar), ',') from ma_job_driver u where u.job_id=?2 and "
//            + "u.job_id in(select id from ma_jobs where status=?1 ) ")
//    String list(String satus, Long jobId);
//
//    @Modifying
//    @Query(nativeQuery = true, value = "delete from ma_job_driver  where job_id=?2 and job_id in(select id from ma_jobs where status=?1 ) ")
//    void deleteOldDriverJob(String satus, Long jobId);

}
