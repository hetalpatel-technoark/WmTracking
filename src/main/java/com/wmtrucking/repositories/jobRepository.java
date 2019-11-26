/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.entities.MaJobs;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface jobRepository extends JpaRepository<MaJobs, Long> {

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 ORDER BY u.id DESC")
    List<MaJobs> list(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and u.id=?2")
    MaJobs findone(String satus, Long id);
}
