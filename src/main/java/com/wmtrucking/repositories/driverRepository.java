/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.entities.MaDriver;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface driverRepository extends JpaRepository<MaDriver, Long> {

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where u.status!=?1 ORDER BY u.id desc ")
    List<MaDriver> list(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where u.status=?1")
    List<MaDriver> activeList(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where u.status=?1 and id not in (select driver_id from ma_job_driver where  job_id=?2)")
    List<MaDriver> driverList(String satus,Long job_id);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where u.status!=?1 and u.id=?2")
    MaDriver findone(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where (u.status=?1 or u.status='Inactive' ) and u.email=?2")
    MaDriver checkEmail(String satus, String email);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where (u.status=?1 or u.status='Inactive' ) and u.mobile=?2")
    MaDriver checkMobile(String satus, String mobile);
//    @Query(nativeQuery = true, value = "select u.* from ma_driver u where (u.status=?1 or u.status='Inactive' ) and u.mobile=?2 and u.countrycode=?3")
//    MaDriver checkMobile(String satus, String mobile, String countrycode);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where (u.status=?1 or u.status='Inactive' ) and u.id=?2 and "
            + "u.id not in (select driver_id from ma_job_driver ) ")
    MaDriver findoneDelete(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_driver u where u.id=?1 and u.id not in (select driver_id from ma_job_driver ) ")
    MaDriver findoneEdit(Long id);

//    @Query(nativeQuery = true, value = "select count(u.id) from ma_driver u where (u.status=?1 or u.status='Inactive' ) and "
//            + "u.id in(select driver_id from ma_job_driver where job_id in (select id from ma_jobs where status=?1 and cast(jobdate as date)=?2 )) ")
//    Long count(String satus, Date createddate);
    @Query(nativeQuery = true, value = " select count(u.id) from ma_driver u where (u.status=?1 or u.status='Inactive' ) and "
            + " u.id in(select driver_id from ma_job_driver where cast(createddate as date)=?2 and job_id in (select id from ma_jobs where status=?1)) ")
    Long count(String satus, Date createddate);

}
