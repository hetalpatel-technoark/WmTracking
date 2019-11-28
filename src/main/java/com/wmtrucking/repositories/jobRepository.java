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

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 ORDER BY u.id desc")
    List<MaJobs> list(String satus);

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and u.id=?2")
    MaJobs findone(String satus, Long id);

    @Query(nativeQuery = true, value = "select count(u.id) from ma_jobs u where u.status=?1")
    Long count(String satus);

    @Query(nativeQuery = true, value = "SELECT to_char(jobdate,'MON-YY'),count(id) FROM ma_jobs where jobdate is not null and status='Active'"
            + " group by to_char(jobdate,'MON-YY'),extract (year from (jobdate)),extract (month from (jobdate)) "
            + "order by extract(year from (jobdate)),extract (month from (jobdate))")
    public List<Object[]> findMonthWiseJob();

    @Query(nativeQuery = true, value = "SELECT (select firstname from ma_driver where id=u.driver_id) as name , count(u.id) FROM ma_jobs u where u.status='Active' group by u.driver_id")
    public List<Object[]> findDriverWiseJob();

}
