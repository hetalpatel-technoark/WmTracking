/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.repositories.customerRepository;
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

}
