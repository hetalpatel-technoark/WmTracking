/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.repositories.driverRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class driverService {

    @Autowired
    driverRepository dRepository;

    public void save(MaDriver maDriver) {
        dRepository.save(maDriver);
    }

    public List<MaDriver> list(String satus) {
        return dRepository.list(satus);
    }

    public MaDriver findone(String satus, Long id) {
        return dRepository.findone(satus, id);
    }

    public MaDriver findoneDelete(String satus, Long id) {
        return dRepository.findoneDelete(satus, id);
    }

    public Long count(String satus, Date createddate) {
        return dRepository.count(satus,createddate);
    }

    public MaDriver checkEmail(String satus, String email) {
        return dRepository.checkEmail(satus, email);
    }

    public MaDriver checkMobile(String satus, String mobile) {
        return dRepository.checkMobile(satus, mobile);
    }

    public List<MaDriver> activeList(String satus) {
        return dRepository.activeList(satus);
    }
    
    public  MaDriver findoneEdit(String satus, Long id){
    return dRepository.findoneEdit(satus, id);
    }
}
