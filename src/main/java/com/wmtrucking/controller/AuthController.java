/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaAuthobject;
import com.wmtrucking.services.authService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1003
 */
@RequestMapping(value = "/auth")
@Scope("request")
@Controller
public class AuthController {

    @Autowired
    authService auService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String access(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("username") == null || request.getParameter("username").equals("")) {
            model.addAttribute("error", "Username and password is mandetory.");
            return "index";
        }
        if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
            model.addAttribute("error", "Username and password is mandetory.");
            return "index";
        }
        MaAuthobject accounts = auService.findUser(request.getParameter("username"), request.getParameter("password"));
        if (accounts == null) {
            model.addAttribute("error", "Username or password is incorrect.");
            return "index";
        }
        SessionUtils sessionUtils = new SessionUtils();
        sessionUtils.setSessionValue(request, Constant.AUTHSESSION.toString(), accounts);
        return "redirect:/Dashboard/Dashboard";
    }

}
