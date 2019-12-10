/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ma_job_tracking")
@NamedQueries({
    @NamedQuery(name = "MaJobTracking.findAll", query = "SELECT m FROM MaJobTracking m")})
public class MaJobTracking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "latitude")
    private BigInteger latitude;
    @Column(name = "longitude")
    private BigInteger longitude;
    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private MaDriver driverId;
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne
    private MaJobs jobId;

    public MaJobTracking() {
    }

    public MaJobTracking(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getLatitude() {
        return latitude;
    }

    public void setLatitude(BigInteger latitude) {
        this.latitude = latitude;
    }

    public BigInteger getLongitude() {
        return longitude;
    }

    public void setLongitude(BigInteger longitude) {
        this.longitude = longitude;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public MaDriver getDriverId() {
        return driverId;
    }

    public void setDriverId(MaDriver driverId) {
        this.driverId = driverId;
    }

    public MaJobs getJobId() {
        return jobId;
    }

    public void setJobId(MaJobs jobId) {
        this.jobId = jobId;
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
        if (!(object instanceof MaJobTracking)) {
            return false;
        }
        MaJobTracking other = (MaJobTracking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaJobTracking[ id=" + id + " ]";
    }
    
}
