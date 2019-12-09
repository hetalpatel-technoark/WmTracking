/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "ma_job_driver")
@SequenceGenerator(name = "ma_job_driver_seq", sequenceName = "ma_job_driver_seq", allocationSize = 1)

@NamedQueries({
    @NamedQuery(name = "MaJobDriver.findAll", query = "SELECT m FROM MaJobDriver m")})
public class MaJobDriver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ma_job_driver_seq")

    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private MaDriver driverId;
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne
    private MaJobs jobId;

    public MaJobDriver() {
    }

    public MaJobDriver(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof MaJobDriver)) {
            return false;
        }
        MaJobDriver other = (MaJobDriver) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaJobDriver[ id=" + id + " ]";
    }

}
