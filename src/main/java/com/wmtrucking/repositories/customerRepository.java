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
public interface customerRepository extends JpaRepository<MaCustomer, Long> {

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status!=?1")
    List<MaCustomer> list(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status=?1")
    List<MaCustomer> activeList(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where (u.status=?1 or u.id in(select cust_id from ma_jobs where"
            + " status='Active' and id=?2) )")
    List<MaCustomer> activeListEdit(String satus, Long jobid);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status!=?1 and u.id=?2")
    MaCustomer findone(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status!=?1 and u.email=?2")
    MaCustomer checkEmail(String satus, String email);

//    @Query(nativeQuery = true, value = "select count(u.id) from ma_customer u where u.status!=?1")
//    Long count(String satus);
    @Query(nativeQuery = true, value = "select count(u.id) from ma_customer u where (u.status=?1 or u.status='Inactive' ) and "
            + "u.id in(select customer_id from ma_job_customer where job_id in (select id from ma_jobs where status=?1 and cast(jobdate as date)=?2 )) ")
    Long count(String satus, Date jobdate);

//    @Query(nativeQuery = true, value = "select u.* from ma_customer u where (u.status=?1 or u.status='Inactive') and u.id=?2 and u.id not in "
//            + "(select cust_id from ma_jobs where status=?1) ")
//    MaCustomer findoneDelete(String satus, Long id);
    @Query(nativeQuery = true, value = "select u.* from ma_customer u where (u.status=?1 or u.status='Inactive') and u.id=?2 and u.id not in "
            + "(select customer_id from ma_job_customer) ")
    MaCustomer findoneDelete(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.id=?1 and u.id not in (select customer_id from ma_job_customer ) ")
    MaCustomer findoneEdit(Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where (u.status=?1 or u.status='Inactive' ) and u.phone=?2")
    MaCustomer checkMobile(String satus, String mobile);
//    @Query(nativeQuery = true, value = "select u.* from ma_customer u where (u.status=?1 or u.status='Inactive' ) and u.phone=?2 and u.countrycode=?3")
//    MaCustomer checkMobile(String satus, String mobile,String countrycode);

}
