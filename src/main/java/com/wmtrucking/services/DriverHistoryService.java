/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaDriverHistory;
import com.wmtrucking.repositories.DriverHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DriverHistoryService {

   @Autowired
   DriverHistoryRepository  driverHistoryRepository;

    public void save(MaDriverHistory maDriverHistory) {
        driverHistoryRepository.save(maDriverHistory);
    }
   
}
