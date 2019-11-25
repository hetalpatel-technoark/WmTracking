/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Admin
 */
@RequestMapping(value = "/Dashboard")
@Scope("request")
@Controller
public class DashboardController {

    @RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest request, HttpServletResponse response) {

        return "Dashboard/Dashboard";
    }

}
