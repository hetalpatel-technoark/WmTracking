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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ma_driver_history")
@SequenceGenerator(name = "ma_driver_history_seq", sequenceName = "ma_driver_history_seq", allocationSize = 1)

@NamedQueries({
    @NamedQuery(name = "MaDriverHistory.findAll", query = "SELECT m FROM MaDriverHistory m")})
public class MaDriverHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "licensenumber")
    private String licensenumber;
    @Size(max = 2147483647)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 2147483647)
    @Column(name = "middlename")
    private String middlename;
    @Size(max = 2147483647)
    @Column(name = "lastname")
    private String lastname;
    @Size(max = 2147483647)
    @Column(name = "address1")
    private String address1;
    @Size(max = 2147483647)
    @Column(name = "address2")
    private String address2;
    @Size(max = 2147483647)
    @Column(name = "address3")
    private String address3;
    @Size(max = 2147483647)
    @Column(name = "city")
    private String city;
    @Size(max = 2147483647)
    @Column(name = "state")
    private String state;
    @Size(max = 2147483647)
    @Column(name = "country")
    private String country;
    @Size(max = 2147483647)
    @Column(name = "pincode")
    private String pincode;
    @Size(max = 2147483647)
    @Column(name = "mobile")
    private String mobile;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ma_driver_history_seq")
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "status")
    private String status;
    @Column(name = "otp")
    private BigInteger otp;
    @Column(name = "otp_expire_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date otpExpireTime;
    @Column(name = "createddate")
    @Temporal(TemporalType.DATE)
    private Date createddate;
    @Size(max = 2147483647)
    @Column(name = "countrycode")
    private String countrycode;
    @Size(max = 2147483647)
    @Column(name = "password")
    private String password;
    @Size(max = 2147483647)
    @Column(name = "drivernumber")
    private String drivernumber;
    @Size(max = 2147483647)
    @Column(name = "driverlicense")
    private String driverlicense;
    @Size(max = 2147483647)
    @Column(name = "companyname")
    private String companyname;

    public MaDriverHistory() {
    }

    public MaDriverHistory(Long id) {
        this.id = id;
    }

    public String getLicensenumber() {
        return licensenumber;
    }

    public void setLicensenumber(String licensenumber) {
        this.licensenumber = licensenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getOtp() {
        return otp;
    }

    public void setOtp(BigInteger otp) {
        this.otp = otp;
    }

    public Date getOtpExpireTime() {
        return otpExpireTime;
    }

    public void setOtpExpireTime(Date otpExpireTime) {
        this.otpExpireTime = otpExpireTime;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDrivernumber() {
        return drivernumber;
    }

    public void setDrivernumber(String drivernumber) {
        this.drivernumber = drivernumber;
    }

    public String getDriverlicense() {
        return driverlicense;
    }

    public void setDriverlicense(String driverlicense) {
        this.driverlicense = driverlicense;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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
        if (!(object instanceof MaDriverHistory)) {
            return false;
        }
        MaDriverHistory other = (MaDriverHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wmtrucking.entities.MaDriverHistory[ id=" + id + " ]";
    }

}
