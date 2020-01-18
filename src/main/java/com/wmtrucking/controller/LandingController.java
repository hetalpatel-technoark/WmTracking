/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaAuthobject;
import com.wmtrucking.services.authService;
import com.wmtrucking.utils.CommonUtils;
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
    @Autowired
    authService authService;

    @RequestMapping(value = "/")
    public String Root(HttpServletRequest request) {

        CommonUtils commonUtils = new CommonUtils();
        String uuid = commonUtils.getCookieValue(request, Constant.COOKIE_NAME.toString());
        if (uuid != null) {
            Long accountid = Long.parseLong(commonUtils.decryptAESURL(uuid));
            System.out.println("accountid...." + accountid);
            MaAuthobject maAuthobject = authService.findOneUser(accountid);
            if (maAuthobject != null) {
                SessionUtils sessionUtils = new SessionUtils();
                sessionUtils.setSessionValue(request, Constant.AUTHSESSION.toString(), maAuthobject);
                return "redirect:/Dashboard/Dashboard";
            }
        } else if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            return "auth/login";
        }
        return "redirect:/Dashboard/Dashboard";
    }
}
