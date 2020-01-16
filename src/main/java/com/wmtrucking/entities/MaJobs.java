/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
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

    @Size(max = 2147483647)
    @Column(name = "jobnumber")
    private String jobnumber;
    @Size(max = 4000)
    @Column(name = "notes")
    private String notes;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "request_status")
    private String requestStatus;
    @Column(name = "fromlatitude")
    private BigDecimal fromlatitude;
    @Column(name = "tolatitude")
    private BigDecimal tolatitude;
    @Column(name = "fromlongitude")
    private BigDecimal fromlongitude;
    @Column(name = "tolongitude")
    private BigDecimal tolongitude;
    @Column(name = "totaljobcount")
    private Long totaljobcount;
    @OneToMany(mappedBy = "jobid")
    private List<MaInvoice> maInvoiceList;
    @OneToMany(mappedBy = "jobId")
    private List<MaJobTransaction> maJobTransactionList;
    @OneToMany(mappedBy = "jobId")
    private List<MaJobCustomer> maJobCustomerList;

    @Column(name = "isarchive")
    private Boolean isarchive;
    
    @Column(name = "hauloff")
    private Boolean hauloff;

    @Column(name = "haulback")
    private Boolean haulback;
    @Column(name = "common")
    private Boolean common;
    @Column(name = "hourly")
    private Boolean hourly;
    @Column(name = "sand")
    private Boolean sand;
    @Column(name = "selectfill")
    private Boolean selectfill;
//    @Size(max = 2147483647)
//    @Column(name = "request_status")
//    private String requestStatus;
    @JoinColumn(name = "driverid", referencedColumnName = "id")
    @ManyToOne()
    private MaDriver driverid;
    @OneToMany(mappedBy = "jobId")
    private List<MaJobTracking> maJobTrackingList;
    @Column(name = "job_status")
    private String job_status;

    @Column(name = "price")
    private String Price;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ma_jobs_seq")

    @Column(name = "id")
    private Long id;
    @Column(name = "jobname")
    private String jobname;
    @Column(name = "jobdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobdate;

    @Column(name = "other")
    private String other;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "state")
    private String state;

    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "address3")
    private String address3;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "lodingaddress")
    private String lodingaddress;
    @Column(name = "dumpingaddress")
    private String dumpingaddress;

    @Column(name = "createddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "modifiedddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedddate;

//    @Column(name = "createdby")
//    private Long createdby;
    @OneToMany(mappedBy = "jobId")
    private List<MaJobDriver> maJobDriverList;
    @JoinColumn(name = "createdby", referencedColumnName = "authid")
    @ManyToOne
    private MaAuthobject createdby;

    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    @ManyToOne
    private MaCustomer custId;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne
    private MaDriver driverId;

    public MaJobs() {
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public MaJobs(Long id) {
        this.id = id;
    }

    public Boolean getIsarchive() {
        return isarchive;
    }

    public void setIsarchive(Boolean isarchive) {
        this.isarchive = isarchive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public MaAuthobject getCreatedby() {
        return createdby;
    }

    public void setCreatedby(MaAuthobject createdby) {
        this.createdby = createdby;
    }

    public String getLodingaddress() {
        return lodingaddress;
    }

    public void setLodingaddress(String lodingaddress) {
        this.lodingaddress = lodingaddress;
    }

    public String getDumpingaddress() {
        return dumpingaddress;
    }

    public void setDumpingaddress(String dumpingaddress) {
        this.dumpingaddress = dumpingaddress;
    }

    public Date getModifiedddate() {
        return modifiedddate;
    }

    public void setModifiedddate(Date modifiedddate) {
        this.modifiedddate = modifiedddate;
    }

    public Boolean getSelectfill() {
        return selectfill;
    }

    public void setSelectfill(Boolean selectfill) {
        this.selectfill = selectfill;
    }

    public Boolean getCommon() {
        return common;
    }

    public void setCommon(Boolean common) {
        this.common = common;
    }

    public Boolean getHourly() {
        return hourly;
    }

    public void setHourly(Boolean hourly) {
        this.hourly = hourly;
    }

    public Boolean getSand() {
        return sand;
    }

    public void setSand(Boolean sand) {
        this.sand = sand;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

//    public String getRequestStatus() {
//        return requestStatus;
//    }
//
//    public void setRequestStatus(String requestStatus) {
//        this.requestStatus = requestStatus;
//    }
    public List<MaJobDriver> getMaJobDriverList() {
        return maJobDriverList;
    }

    public void setMaJobDriverList(List<MaJobDriver> maJobDriverList) {
        this.maJobDriverList = maJobDriverList;
    }

    public Boolean getHauloff() {
        return hauloff;
    }

    public void setHauloff(Boolean hauloff) {
        this.hauloff = hauloff;
    }

    public Boolean getHaulback() {
        return haulback;
    }

    public void setHaulback(Boolean haulback) {
        this.haulback = haulback;
    }

//    public String getRequestStatus() {
//        return requestStatus;
//    }
//
//    public void setRequestStatus(String requestStatus) {
//        this.requestStatus = requestStatus;
//    }
    public MaDriver getDriverid() {
        return driverid;
    }

    public void setDriverid(MaDriver driverid) {
        this.driverid = driverid;
    }

    public List<MaJobTracking> getMaJobTrackingList() {
        return maJobTrackingList;
    }

    public void setMaJobTrackingList(List<MaJobTracking> maJobTrackingList) {
        this.maJobTrackingList = maJobTrackingList;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public List<MaJobCustomer> getMaJobCustomerList() {
        return maJobCustomerList;
    }

    public void setMaJobCustomerList(List<MaJobCustomer> maJobCustomerList) {
        this.maJobCustomerList = maJobCustomerList;
    }

    public List<MaJobTransaction> getMaJobTransactionList() {
        return maJobTransactionList;
    }

    public void setMaJobTransactionList(List<MaJobTransaction> maJobTransactionList) {
        this.maJobTransactionList = maJobTransactionList;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
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

    public BigDecimal getFromlatitude() {
        return fromlatitude;
    }

    public void setFromlatitude(BigDecimal fromlatitude) {
        this.fromlatitude = fromlatitude;
    }

    public BigDecimal getTolatitude() {
        return tolatitude;
    }

    public void setTolatitude(BigDecimal tolatitude) {
        this.tolatitude = tolatitude;
    }

    public BigDecimal getFromlongitude() {
        return fromlongitude;
    }

    public void setFromlongitude(BigDecimal fromlongitude) {
        this.fromlongitude = fromlongitude;
    }

    public BigDecimal getTolongitude() {
        return tolongitude;
    }

    public void setTolongitude(BigDecimal tolongitude) {
        this.tolongitude = tolongitude;
    }

    public Long getTotaljobcount() {
        return totaljobcount;
    }

    public void setTotaljobcount(Long totaljobcount) {
        this.totaljobcount = totaljobcount;
    }

    public List<MaInvoice> getMaInvoiceList() {
        return maInvoiceList;
    }

    public void setMaInvoiceList(List<MaInvoice> maInvoiceList) {
        this.maInvoiceList = maInvoiceList;
    }

}
