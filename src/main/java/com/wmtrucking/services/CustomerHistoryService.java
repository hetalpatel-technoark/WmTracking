/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaCustomerHistory;
import com.wmtrucking.repositories.CustomerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CustomerHistoryService {

    @Autowired
    CustomerHistoryRepository customerHistoryRepository;

    public void save(MaCustomerHistory maCustomerHistory) {
        customerHistoryRepository.save(maCustomerHistory);
    }

}
