/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class JobPojo {

    String customername, jobname, jobnumber, status, transectionstatus, drivername,jobdate,starttime,endtime;
   
    Long id, totaldumps, completeddumps, drivercount;

//    String jobname, jobnumber, transectionstatus, drivername;
//    Long id, totaljobcount, transactioncount, drivercount;
//    Date jobdate;
    BigDecimal fromlatitude, fromlongitude, tolatitude, tolongitude;
    public String getCustomername() {
        return customername;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransectionstatus() {
        return transectionstatus;
    }

    public void setTransectionstatus(String transectionstatus) {
        this.transectionstatus = transectionstatus;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public BigDecimal getFromlatitude() {
        return fromlatitude;
    }

    public void setFromlatitude(BigDecimal fromlatitude) {
        this.fromlatitude = fromlatitude;
    }

    public BigDecimal getFromlongitude() {
        return fromlongitude;
    }

    public void setFromlongitude(BigDecimal fromlongitude) {
        this.fromlongitude = fromlongitude;
    }

    public BigDecimal getTolatitude() {
        return tolatitude;
    }

    public void setTolatitude(BigDecimal tolatitude) {
        this.tolatitude = tolatitude;
    }

    public BigDecimal getTolongitude() {
        return tolongitude;
    }

    public void setTolongitude(BigDecimal tolongitude) {
        this.tolongitude = tolongitude;
    }

    public String getJobdate() {
        return jobdate;
    }

    public void setJobdate(String jobdate) {
        this.jobdate = jobdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotaldumps() {
        return totaldumps;
    }

    public void setTotaldumps(Long totaldumps) {
        this.totaldumps = totaldumps;
    }

    public Long getCompleteddumps() {
        return completeddumps;
    }

    public void setCompleteddumps(Long completeddumps) {
        this.completeddumps = completeddumps;
    }

    public Long getDrivercount() {
        return drivercount;
    }

    public void setDrivercount(Long drivercount) {
        this.drivercount = drivercount;
    }

}
