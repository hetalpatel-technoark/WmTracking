/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ma_jobs")
@SequenceGenerator(name = "ma_jobs_seq", sequenceName = "ma_jobs_seq", allocationSize = 1)

@NamedQueries({
    @NamedQuery(name = "MaJobs.findAll", query = "SELECT m FROM MaJobs m")})
public class MaJobs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ma_jobs_seq")

    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "jobnumber")
    private String jobnumber;
    @Column(name = "jobdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobdate;
    @Size(max = 2147483647)
    @Column(name = "hauloff")
    private String hauloff;
    @Size(max = 2147483647)
    @Column(name = "haulback")
    private String haulback;

    @Size(max = 400)
    @Column(name = "notes")
    private String notes;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    @ManyToOne
    private MaCustomer custId;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private MaDriver driverId;

    public MaJobs() {
    }

    public MaJobs(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public Date getJobdate() {
        return jobdate;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public void setJobdate(Date jobdate) {
        this.jobdate = jobdate;
    }

    public String getHauloff() {
        return hauloff;
    }

    public void setHauloff(String hauloff) {
        this.hauloff = hauloff;
    }

    public String getHaulback() {
        return haulback;
    }

    public void setHaulback(String haulback) {
        this.haulback = haulback;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MaCustomer getCustId() {
        return custId;
    }

    public void setCustId(MaCustomer custId) {
        this.custId = custId;
    }

    public MaDriver getDriverId() {
        return driverId;
    }

    public void setDriverId(MaDriver driverId) {
        this.driverId = driverId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaJobs)) {
            return false;
        }
        MaJobs other = (MaJobs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaJobs[ id=" + id + " ]";
    }

}
