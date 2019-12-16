/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    SessionUtils sessionUtils;

    @ModelAttribute(value = "customer")
    public void customer(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest request, HttpServletResponse response) {

        model.addAttribute("customer", cuService.count(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("job", joService.count(Constant.ACTIVE.toString()));
        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), Constant.STARTED.toString(), new Date()));
        model.addAttribute("countDumpingPickup", joService.countDumpingDone(Constant.ACTIVE.toString(), Constant.ENDED.toString(), new Date()));

//        List<Object[]> monthWiseJob = joService.findMonthWiseJob();
//        if (!monthWiseJob.isEmpty()) {
//            model.addAttribute("monthWiseJob", monthWiseJob);
//        }
//        List<Object[]> DriverWiseJob = joService.findDriverWiseJob();
//        if (!DriverWiseJob.isEmpty()) {
//            model.addAttribute("DriverWiseJob", DriverWiseJob);
//        }
//        List<Object[]> customerWiseJob = joService.findCustomerWiseJob();
//        if (!customerWiseJob.isEmpty()) {
//            model.addAttribute("customerWiseJob", customerWiseJob);
//        }
        // contains only date information without time
        List<JobPojo> maJobsesList = joService.getJobList(Constant.ACTIVE.toString(), new Date());

        model.addAttribute("maJobsesList", maJobsesList);
        return "Dashboard/Dashboard";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace();
        ex.printStackTrace(new PrintWriter(errors));
        ModelAndView mav = new ModelAndView();
        // mav.setViewName("redirect:/auth/authenticate");
        mav.setViewName("redirect:/");
        return mav;
    }

}
