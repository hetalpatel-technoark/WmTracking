/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
public class LandingController {

    @Autowired
    SessionUtils sessionUtils;

    @RequestMapping(value = "/")
    public String Root(HttpServletRequest request) {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            return "auth/login";
        }
        return "redirect:/Dashboard/Dashboard";
    }
}
