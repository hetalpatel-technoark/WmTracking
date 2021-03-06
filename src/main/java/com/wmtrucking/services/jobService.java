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

    public MaJobs findone(String satus, Long id, Boolean isarchive) {
        return jobRepository.findone(satus, id, isarchive);
    }

    public Long totalDumpCount(String satus, Date createdDate) {
        return jobRepository.totalDumpCount(satus, createdDate);
    }

    public MaJobs findoneCompletedjob(String satus, Long id) {
        return jobRepository.findoneCompletedjob(satus, id);
    }

    public Long count(String satus, Date createddate) {
        return jobRepository.count(satus, createddate);
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

    public MaJobs findPendingJob(String satus, Long id, Boolean isarchive) {
        return jobRepository.findPendingJob(satus, id, isarchive);
    }

//    public List<JobPojo> getJobList(String satus, Date jobdate) {
//        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer where "
//                + "job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
//                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
//                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
//                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
//                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
//                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
//                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
//                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
//                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
//                + "            from ma_jobs j where status=? and cast(j.jobdate as date)=? ORDER BY j.id desc";
//        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
//        return jobPojo;
//    }
    public List<JobPojo> getJobList(String satus, Date createddate) {
//        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer where "
//                + "job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
//                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
//                + "(case when (j.job_status='Completed') then 'Completed' else case when (j.job_status='Pending') then "
//                + "	(case when ((select count(id) from ma_job_transaction where job_id=j.id ) >0 ) then 'Active'"
//                + " else 'Pending' end ) end end ) as jobStatus,"
//                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
//                + "(select count(id) from ma_job_transaction where job_id=j.id and status='Started')as pickupddumps,"
//                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
//                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
//                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
//                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
//                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
//                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
//                + "            from ma_jobs j where status=? and cast(j.createddate as date)=? and j.isarchive='False' ORDER BY j.id desc";
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer where "
                + "         job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "         j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "         (case when (j.job_status='Completed') then 'Completed' else case when (j.job_status='Pending') then "
                + "         	(case when ((select count(id) from ma_job_transaction where job_id=j.id ) >0 ) then 'Active'"
                + "          else 'Pending' end ) end end ) as jobStatus,"
                + "    (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "         (select count(id) from ma_job_transaction where job_id=j.id and starttime is not null)as pickupddumps,"
                + "    (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "   else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + " else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + " else 2 end end end) as Transectionstatus,"
                + "		 case when trim(fromlatitude)  ~ '^-?(\\d+\\.?\\d*)$|(\\d*\\.?\\d+)$' then cast(fromlatitude as numeric) else round(dms2dd(cast (fromlatitude as text)),9) end as fromlatitude, "
                + "		 case when trim(fromlongitude) ~ '^-?(\\d+\\.?\\d*)$|(\\d*\\.?\\d+)$' then cast(fromlongitude as numeric) else round(dms2dd(cast (fromlongitude as text)),9) end as fromlongitude, "
                + "		 case when trim(tolatitude)    ~ '^-?(\\d+\\.?\\d*)$|(\\d*\\.?\\d+)$' then cast(tolatitude as numeric) else round(dms2dd(cast (tolatitude as text)),9) end as tolatitude, "
                + "		 case when trim(tolongitude)   ~ '^-?(\\d+\\.?\\d*)$|(\\d*\\.?\\d+)$' then cast(tolongitude as numeric) else round(dms2dd(cast (tolongitude as text)),9) end as tolongitude, "
                + "		(select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "        from ma_jobs j where status=? and cast(j.createddate as date)=? and j.isarchive='False' ORDER BY j.id desc";

        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, createddate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> searchJobList(String satus, Date createddate, String searchText) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer where "
                + "job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "(case when (j.job_status='Completed') then 'Completed' else case when (j.job_status='Pending') then "
                + "	(case when ((select count(id) from ma_job_transaction where job_id=j.id ) >0 ) then 'Active'"
                + " else 'Pending' end ) end end ) as jobStatus,"
                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "(select count(id) from ma_job_transaction where job_id=j.id and starttime is not null)as pickupddumps,"
                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "            from ma_jobs j where status = ? and cast(j.createddate as date) = cast( ? as date) and "
                + "         (j.jobname ILIKE ? or j.jobnumber = ? ) ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, createddate, "%" + searchText + "%", searchText}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getJobList(String satus, Boolean isarchive) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer"
                + "				 where job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "(case when (j.job_status='Completed') then 'Completed' else case when (j.job_status='Pending') then "
                + "	(case when ((select count(id) from ma_job_transaction where job_id=j.id ) >0 ) then 'Active'"
                + " else 'Pending' end ) end end ) as jobStatus,"
                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "(select count(id) from ma_job_transaction where job_id=j.id and starttime is not null)as pickupddumps,"
                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "            from ma_jobs j where j.status=? and j.isarchive=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, isarchive}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> searchJobList(String satus, Boolean isarchive, String searchtext) {
        String query = "select j.id, (select string_agg(firstname, ', ') from ma_customer where id in (select customer_id from ma_job_customer"
                + "				 where job_id=j.id))as customername ,(SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),"
                + "j.jobname,j.jobnumber, j.totaljobcount as totaldumps, j.status,"
                + "(case when (j.job_status='Completed') then 'Completed' else case when (j.job_status='Pending') then "
                + "	(case when ((select count(id) from ma_job_transaction where job_id=j.id ) >0 ) then 'Active'"
                + " else 'Pending' end ) end end ) as jobStatus,"
                + "            (select count(id) from ma_job_transaction where job_id=j.id and status='Ended')as completeddumps,"
                + "(select count(id) from ma_job_transaction where job_id=j.id and starttime is not null)as pickupddumps,"
                + "            (select count(id) from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivercount,"
                + "             ( case when ((select count(id) from ma_job_transaction where job_id=j.id and status='Ended') = j.totaljobcount) then 0 "
                + "                else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') =0) then 1 "
                + "              else case when ( (select count(id) from ma_job_transaction where job_id=j.id and status='Ended') < j.totaljobcount) then 3 "
                + "              else 2 end end end) as Transectionstatus,fromlatitude, fromlongitude,tolatitude,tolongitude,"
                + "            (select string_agg(firstname, ', ') from ma_driver where id in (select driver_id from ma_job_driver where job_id=j.id))as drivername "
                + "            from ma_jobs j where j.status=? and j.isarchive=? and (j.jobname ILIKE ? or j.jobnumber = ? ) ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{satus, isarchive, searchtext, searchtext}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getStartJob(Date jobdate) {
        String query = "select j.id, (SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),(SELECT TO_CHAR(t.starttime, 'Month DD, YYYY hh:mi:ssam') as starttime),"
                + "j.jobname,j.jobnumber,( case when ((select count(id) from ma_job_transaction where id=t.id and job_id=j.id and driverid=t.driverid and "
                + "status='Ended') > 0) then 0 else case when ((select count(id) from ma_job_transaction where id=t.id and job_id=j.id and "
                + "driverid=t.driverid and status='Started') >0) then 1 else 2 end end ) as Transectionstatus,"
                + "(select CONCAT(firstname ,' ',middlename,' ',lastname)  from ma_driver where id in (select driver_id from ma_job_driver"
                + "		where job_id=j.id and  driver_id=t.driverid and driver_id in(select driverid from ma_job_transaction where"
                + "		 driver_id=t.driverid and job_id=j.id)))as drivername  "
                + "            from ma_jobs j left join ma_job_transaction t on j.id=t.job_id  where j.status='Active' and "
                + " cast(t.starttime as date)=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public List<JobPojo> getEndJob(Date jobdate) {
        String query = "select j.id, (SELECT TO_CHAR(j.jobdate, 'Month DD, YYYY') as jobdate),(SELECT TO_CHAR(t.endtime, 'Month DD, YYYY hh:mi:ssam') as endtime),"
                + "j.jobname,j.jobnumber,( case when ((select count(id) from ma_job_transaction where id=t.id and job_id=j.id and driverid=t.driverid and"
                + " status='Ended') > 0) then 0  else case when ((select count(id) from ma_job_transaction where id=t.id and job_id=j.id and "
                + "driverid=t.driverid and status='Started') >0) then 1  else 2 end end ) as Transectionstatus,"
                + "(select CONCAT(firstname ,' ',middlename,' ',lastname)  from ma_driver where id in (select driver_id from ma_job_driver"
                + "		where job_id=j.id and  driver_id=t.driverid and driver_id in(select driverid from ma_job_transaction where"
                + "		 driver_id=t.driverid and job_id=j.id)))as drivername  "
                + "            from ma_jobs j left join ma_job_transaction t on j.id=t.job_id  where j.status='Active' and "
                + " cast(t.endtime as date)=? ORDER BY j.id desc";
        List<JobPojo> jobPojo = jdbcTemplate.query(query, new Object[]{jobdate}, new BeanPropertyRowMapper<JobPojo>(JobPojo.class));
        return jobPojo;
    }

    public Long countDumpingPickup(String satus, Date starttime) {
        return jobRepository.countDumpingPickup(satus, starttime);
    }

    public Long countDumpingDone(String satus, Date endtime) {
        return jobRepository.countDumpingDone(satus, endtime);
    }

    public List<Object[]> list(String status, String transectionstatus) {
        return jobRepository.list(status, transectionstatus);
    }

    public MaJobs checkJobNumber(String satus, String jobnumber) {
        return jobRepository.checkJobNumber(satus, jobnumber);
    }

}
