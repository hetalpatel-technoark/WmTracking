/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class JobPojo {

    String jobname, jobnumber, transectionstatus, drivername;
    Long id, totaljobcount, transactioncount, drivercount;
    Date jobdate;
    BigDecimal fromlatitude, fromlongitude, tolatitude, tolongitude;

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotaljobcount() {
        return totaljobcount;
    }

    public void setTotaljobcount(Long totaljobcount) {
        this.totaljobcount = totaljobcount;
    }

    public Long getTransactioncount() {
        return transactioncount;
    }

    public void setTransactioncount(Long transactioncount) {
        this.transactioncount = transactioncount;
    }

    public Long getDrivercount() {
        return drivercount;
    }

    public void setDrivercount(Long drivercount) {
        this.drivercount = drivercount;
    }

    public Date getJobdate() {
        return jobdate;
    }

    public void setJobdate(Date jobdate) {
        this.jobdate = jobdate;
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
    
    
}
