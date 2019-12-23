/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaJobs;
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
public interface jobRepository extends JpaRepository<MaJobs, Long> {

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 ORDER BY u.id desc")
    List<MaJobs> list(String satus);

    @Query(nativeQuery = true, value = "select j.id, j.jobname,j.jobnumber,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate), j.totaljobcount, "
            + "(select count(id) from ma_job_transaction where job_id=j.id and status=?2)as transactioncount,"
            + "(select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
            + " ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status=?2) = j.totaljobcount) then 0 "
            + "    else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status=?2) =0) then 1 "
            + "  else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status=?2) < j.totaljobcount) then 3 "
            + "  else 2 end end end) as Transectionstatus,"
            + "(select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
            + "from ma_jobs j where status=?1")
    public List<Object[]> list(String status, String transectionstatus);

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and u.id=?2")
    MaJobs findone(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and u.id=?2 and id not in (select job_id from ma_job_transaction) ")
    MaJobs findPendingJob(String satus, Long id);

    @Query(nativeQuery = true, value = "select count(u.id) from ma_jobs u where u.status=?1")
    Long count(String satus);

    @Query(nativeQuery = true, value = "SELECT to_char(jobdate,'MON-YY'),count(id) FROM ma_jobs where jobdate is not null and status='Active'"
            + " group by to_char(jobdate,'MON-YY'),extract (year from (jobdate)),extract (month from (jobdate)) "
            + "order by extract(year from (jobdate)),extract (month from (jobdate))")
    public List<Object[]> findMonthWiseJob();

    @Query(nativeQuery = true, value = "SELECT (select firstname from ma_driver where id=u.driver_id) as name , count(u.id) FROM ma_jobs u where u.status='Active' group by u.driver_id")
    public List<Object[]> findDriverWiseJob();

    @Query(nativeQuery = true, value = "SELECT (select firstname from ma_customer where id=u.cust_id) as name , "
            + "count(u.id) FROM ma_jobs u where u.status='Active' group by u.cust_id")
    public List<Object[]> findCustomerWiseJob();

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and cast(u.jobdate as date)=?2 ORDER BY u.id desc")
    public List<MaJobs> listOfJob(String satus, Date jobdate);

    @Query(nativeQuery = true, value = "select count(id) from ma_job_transaction where status=?2 "
            + "   and cast(starttime as date)=?3 and job_id in(select id from ma_jobs where status=?1)")
    Long countDumpingPickup(String satus, String transectionStatus, Date starttime);
//    @Query(nativeQuery = true, value = "SELECT SUM(totaljobcount) FROM ma_jobs where status=?1 and id in(select job_id from ma_job_transaction "
//            + "where status=?2 and cast(starttime as date)=?3)")
//    Long countDumpingPickup(String satus, String transectionStatus, Date starttime);

//    @Query(nativeQuery = true, value = "SELECT SUM(totaljobcount) FROM ma_jobs where status=?1 and id in(select job_id from ma_job_transaction "
//            + "where status=?2 and cast(endtime as date)=?3 )")
//    Long countDumpingDone(String satus, String transectionStatus, Date endtime);
    @Query(nativeQuery = true, value = "select count(id) from ma_job_transaction where status=?2 "
            + "   and cast(endtime as date)=?3 and job_id in(select id from ma_jobs where status=?1)")
    Long countDumpingDone(String satus, String transectionStatus, Date endtime);

    @Query(nativeQuery = true, value = "select u.* from ma_jobs u where u.status=?1 and u.jobnumber=?2")
    MaJobs checkJobNumber(String satus, String jobnumber);

}
