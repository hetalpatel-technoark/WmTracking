/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaJobCustomer;
import com.wmtrucking.repositories.jobCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class jobCustomerService {

    @Autowired
    jobCustomerRepository jobcustomerRepository;

    public void save(MaJobCustomer maJobCustomer) {
        jobcustomerRepository.save(maJobCustomer);
    }

}
