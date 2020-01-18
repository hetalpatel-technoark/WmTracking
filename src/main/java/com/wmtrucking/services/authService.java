/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaAuthobject;
import com.wmtrucking.repositories.authRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class authService {
    
    @Autowired
    private authRepository auRepository;
    
    public MaAuthobject findUser(String email, String password) {
        return auRepository.findUser(email, password);
    }
    
    public MaAuthobject findOneUser(Long authid) {
        return auRepository.findOneUser(authid);
    }
}
