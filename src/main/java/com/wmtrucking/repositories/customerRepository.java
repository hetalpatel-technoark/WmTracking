/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaCustomer;
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

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status=?1")
    List<MaCustomer> list(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status=?1 and u.id=?2")
    MaCustomer findone(String satus, Long id);
   
    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status=?1 and u.id=?2 and u.id not in (select cust_id from ma_jobs where status=?1) ")
    MaCustomer findoneDelete(String satus, Long id);


}