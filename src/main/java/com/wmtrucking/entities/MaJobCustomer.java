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
@Table(name = "ma_job_customer")
@SequenceGenerator(name = "ma_job_customer_seq", sequenceName = "ma_job_customer_seq", allocationSize = 1)

@NamedQueries({
    @NamedQuery(name = "MaJobCustomer.findAll", query = "SELECT m FROM MaJobCustomer m")})
public class MaJobCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ma_job_customer_seq")

    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private MaCustomer customerId;
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    @ManyToOne
    private MaJobs jobId;

    public MaJobCustomer() {
    }

    public MaJobCustomer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaCustomer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(MaCustomer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof MaJobCustomer)) {
            return false;
        }
        MaJobCustomer other = (MaJobCustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaJobCustomer[ id=" + id + " ]";
    }

}
