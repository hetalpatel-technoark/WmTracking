/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.repositories.jobRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class jobService {

    @Autowired
    jobRepository jobRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(MaJobs maJobs) {
        jobRepository.save(maJobs);
    }

    public List<MaJobs> list(String satus) {
        return jobRepository.list(satus);
    }

    public MaJobs findone(String satus, Long id) {
        return jobRepository.findone(satus, id);
    }

    public Long count(String satus) {
        return jobRepository.count(satus);
    }

    public List<Object[]> findMonthWiseJob() {
        return jobRepository.findMonthWiseJob();
    }

    public List<Object[]> findDriverWiseJob() {
        return jobRepository.findDriverWiseJob();
    }

    public List<Object[]> findCustomerWiseJob() {
        return jobRepository.findCustomerWiseJob();
    }

    public List<MaJobs> listOfJob(String satus, Date jobdate) {
        return jobRepository.listOfJob(satus, jobdate);
    }

    public MaJobs findPendingJob(String satus, Long id) {
        return jobRepository.findPendingJob(satus, id);
    }

//    public List<JobPojo> getJobList(String satus, Date jobdate) {
//        String query = "select j.id, j.jobname,j.jobnumber,  j.jobdate, j.totaljobcount, "
//                + "           (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as transactioncount,"
//                + "           (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
//                + "            ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
//                + "               else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
//                + "             else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
//                + "             else 2 end end end) as Transectionstatus ,"
//                + "		(select string_agg(firstname, ', ')"
//                + "  from ma_driver where id in (select driver_id from ma_job_driver"
//                + "			where job_id=j.id))as drivername , fromlatitude, fromlongitude,tolatitude,tolongitude"
//                + "			from ma_jobs j where status=? and cast(j.jobdate as date)=? ORDER BY j.id desc";
//        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
//        return jobPojo;
//    }
    public List<JobPojo> getJobList(String satus, Date jobdate) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer"
                + "				 where job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "            from ma_jobs j where status=? and cast(j.jobdate as date)=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getJobList(String satus) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer"
                + "				 where job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "            from ma_jobs j where status=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getStartJob( Date jobdate) {
        String query = "select j.id, (SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),(SELECT TO_CHAR(t.starttime, 'Month DD, YYYY') as starttime),"
                + "j.jobname,j.jobnumber,( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,"
                + "(select CONCAT(firstname ,' ',middlename,' ',lastname)  from ma_driver where id in (select driver_id from ma_job_driver"
                + "		where job_id=j.id and  driver_id=t.driverid and driver_id in(select driverid from ma_job_transaction where"
                + "		 driver_id=t.driverid and job_id=j.id)))as drivername  "
                + "            from ma_jobs j left join ma_job_transaction t on j.id=t.job_id  where j.status='Active' and "
                + " cast(t.starttime as date)=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{ jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getEndJob( Date jobdate) {
        String query = "select j.id, (SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),(SELECT TO_CHAR(t.starttime, 'Month DD, YYYY') as starttime),"
                + "j.jobname,j.jobnumber,( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,"
                + "(select CONCAT(firstname ,' ',middlename,' ',lastname)  from ma_driver where id in (select driver_id from ma_job_driver"
                + "		where job_id=j.id and  driver_id=t.driverid and driver_id in(select driverid from ma_job_transaction where"
                + "		 driver_id=t.driverid and job_id=j.id)))as drivername  "
                + "            from ma_jobs j left join ma_job_transaction t on j.id=t.job_id  where j.status='Active' and "
                + " cast(t.endtime as date)=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{ jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public Long countDumpingPickup(String satus, String transectionStatus, Date starttime) {
        return jobRepository.countDumpingPickup(satus, transectionStatus, starttime);
    }

    public Long countDumpingDone(String satus, String transectionStatus, Date endtime) {
        return jobRepository.countDumpingDone(satus, transectionStatus, endtime);
    }

    public List<Object[]> list(String status, String transectionstatus) {
        return jobRepository.list(status, transectionstatus);
    }

    public MaJobs checkJobNumber(String satus, String jobnumber) {
        return jobRepository.checkJobNumber(satus, jobnumber);
    }

}
