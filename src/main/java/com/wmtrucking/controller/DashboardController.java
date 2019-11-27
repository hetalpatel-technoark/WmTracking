/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.Constant;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    customerService cuService;
    @Autowired
    jobService joService;
    @Autowired
    driverService drService;

    @RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest request, HttpServletResponse response) {

        model.addAttribute("customer", cuService.count(Constant.ACTIVE.toString()));
        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString()));
        model.addAttribute("job", joService.count(Constant.ACTIVE.toString()));

        List<Object[]> monthWiseJob = joService.findMonthWiseJob();
        if (!monthWiseJob.isEmpty()) {
            model.addAttribute("monthWiseJob", monthWiseJob);
        }

        List<Object[]> DriverWiseJob = joService.findDriverWiseJob();
        if (!DriverWiseJob.isEmpty()) {
            model.addAttribute("DriverWiseJob", DriverWiseJob);
        }
        return "Dashboard/Dashboard";
    }

}
