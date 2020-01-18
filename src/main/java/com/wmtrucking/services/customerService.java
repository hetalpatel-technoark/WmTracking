/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.repositories.customerRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class customerService {

    @Autowired
    customerRepository cuRepository;

    public void save(MaCustomer maCustomer) {
        cuRepository.save(maCustomer);
    }

    public List<MaCustomer> list(String satus) {
        return cuRepository.list(satus);
    }

    public MaCustomer findone(String satus, Long id) {
        return cuRepository.findone(satus, id);
    }

    public MaCustomer findoneDelete(String satus, Long id) {
        return cuRepository.findoneDelete(satus, id);
    }

    public MaCustomer checkEmail(String satus, String email) {
        return cuRepository.checkEmail(satus, email);
    }

    public Long customercount(String satus, Date jobdate) {
        return cuRepository.customercount(satus, jobdate);
    }

    public List<MaCustomer> activeList(String satus) {
        return cuRepository.activeList(satus);
    }

    public List<MaCustomer> activeListEdit(String satus, Long jobid) {
        return cuRepository.activeListEdit(satus, jobid);
    }

    public MaCustomer findoneEdit(Long id) {
        return cuRepository.findoneEdit(id);
    }

    public MaCustomer checkMobile(String satus, String mobile) {
        return cuRepository.checkMobile(satus, mobile);
    }
}
