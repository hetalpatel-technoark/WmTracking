/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaCustomerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface CustomerHistoryRepository extends JpaRepository<MaCustomerHistory, Long> {

//    @Query(nativeQuery = true, value = "select u.* from ma_customer u where u.status!=?1")
//    List<MaCustomer> list(String satus);

    
}
