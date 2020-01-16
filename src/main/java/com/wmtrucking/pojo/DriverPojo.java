/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.pojo;

/**
 *
 * @author Admin
 */
public class DriverPojo {

    Long id,job_id;
    int donejobcount, pickupjobcount;
    String firstname, createddate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJob_id() {
        return job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public int getDonejobcount() {
        return donejobcount;
    }

    public void setDonejobcount(int donejobcount) {
        this.donejobcount = donejobcount;
    }

    public int getPickupjobcount() {
        return pickupjobcount;
    }

    public void setPickupjobcount(int pickupjobcount) {
        this.pickupjobcount = pickupjobcount;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

}
