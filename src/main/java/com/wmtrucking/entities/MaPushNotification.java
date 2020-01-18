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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "ma_push_notification")
@NamedQueries({
    @NamedQuery(name = "MaPushNotification.findAll", query = "SELECT m FROM MaPushNotification m")})
public class MaPushNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pushid")
    private Long pushid;
    @Size(max = 255)
    @Column(name = "devicetoken")
    private String devicetoken;
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Size(max = 25)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "driverid", referencedColumnName = "id")
    @ManyToOne
    private MaDriver driverid;

    public MaPushNotification() {
    }

    public MaPushNotification(Long pushid) {
        this.pushid = pushid;
    }

    public Long getPushid() {
        return pushid;
    }

    public void setPushid(Long pushid) {
        this.pushid = pushid;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MaDriver getDriverid() {
        return driverid;
    }

    public void setDriverid(MaDriver driverid) {
        this.driverid = driverid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pushid != null ? pushid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaPushNotification)) {
            return false;
        }
        MaPushNotification other = (MaPushNotification) object;
        if ((this.pushid == null && other.pushid != null) || (this.pushid != null && !this.pushid.equals(other.pushid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaPushNotification[ pushid=" + pushid + " ]";
    }
    
}
