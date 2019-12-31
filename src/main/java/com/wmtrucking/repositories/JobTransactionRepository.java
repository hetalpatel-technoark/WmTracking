/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaJobTransaction;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Scope(value = "request")
public interface JobTransactionRepository extends JpaRepository<MaJobTransaction, Long> {

    @Query(nativeQuery = true, value = "select count(*) from ma_job_transaction where job_id=?1")
    Long totalJobTransactionCount(Long jobid);

    @Query(nativeQuery = true, value = "select t.* from ma_job_transaction t where cast(t.starttime as date)=?1")
    List<MaJobTransaction> getStartJob(Date startDate);

    @Query(nativeQuery = true, value = "select t.* from ma_job_transaction t where cast(t.endtime as date)=?1")
    List<MaJobTransaction> getEndJob(Date endtime);

}
