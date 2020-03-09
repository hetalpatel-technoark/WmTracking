/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.DateUtils;
import com.wmtrucking.utils.SessionUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        model.addAttribute("job", joService.count(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("totalDump", joService.totalDumpCount(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("countDumpingDone", joService.countDumpingDone(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), new Date()));
        model.addAttribute("customer", cuService.customercount(Constant.ACTIVE.toString(), new Date()));

        // contains only date information without time
        List<JobPojo> maJobsesList = joService.getJobList(Constant.ACTIVE.toString(), new Date());
        model.addAttribute("maJobsesList", maJobsesList);
        model.addAttribute("jobDate", new DateUtils().dateWithFormat(new Date(), "MMMM dd, yyyy"));
        model.addAttribute("showJobDate", "Today Date");

        return "Dashboard/Dashboard";
    }

    @RequestMapping(value = "/NextJobDate/{jobDate}", method = RequestMethod.GET)
    public String NextJobDate(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("jobDate") String jobDate) throws ParseException {

        DateUtils dateutil = new DateUtils();
        Date sourceDate = dateutil.stringToDate(jobDate, "MMMM dd, yyyy");
        Date myDate = dateutil.IncreaseDays(sourceDate, 1);
//        model.addAttribute("customer", cuService.count(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("job", joService.count(Constant.ACTIVE.toString()));
//        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("countDumpingDone", joService.countDumpingDone(Constant.ACTIVE.toString(), myDate));

        model.addAttribute("job", joService.count(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("totalDump", joService.totalDumpCount(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("countDumpingDone", joService.countDumpingDone(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("customer", cuService.customercount(Constant.ACTIVE.toString(), myDate));

        List<JobPojo> maJobsesList = joService.getJobList(Constant.ACTIVE.toString(), myDate);

        model.addAttribute("jobDate", new DateUtils().dateWithFormat(myDate, "MMMM dd, yyyy"));
        model.addAttribute("maJobsesList", maJobsesList);
        return "Dashboard/Dashboard";
    }

    //owner list show after modify ownership
    @RequestMapping(value = "/getJobList", method = RequestMethod.POST)
    @ResponseBody
    public String getJobList(HttpServletRequest request, Model model) throws UnAthorizedUserException {

        List<JobPojo> maJobsesList = null;

        DateUtils dateutil = new DateUtils();
        Date sourceDate = dateutil.stringToDate(request.getParameter("jobdate"), "MMMM dd, yyyy");

        if (request.getParameter("searchtext") != null && !request.getParameter("searchtext").equals(" ")) {
            maJobsesList = joService.searchJobList(Constant.ACTIVE.toString(), sourceDate, request.getParameter("searchtext"));
        } else {
            maJobsesList = joService.getJobList(Constant.ACTIVE.toString(), sourceDate);
        }
        JsonObject response = new JsonObject();
        StringBuilder str = new StringBuilder();

        if (maJobsesList.isEmpty()) {
            str.append("<tr><td colspan='8' style='text-align:center'>No records found</td></tr>");
        } else {
            for (JobPojo jobPojo : maJobsesList) {
                str.append("<tr><td>");
                str.append(jobPojo.getJobname());
                str.append("</td><td>");
                str.append(jobPojo.getJobnumber());
                str.append("</td><td>");
                str.append(jobPojo.getJobdate());
                str.append("</td><td>");
                str.append(jobPojo.getTotaldumps());
                str.append("</td><td>");
                str.append(jobPojo.getPickupddumps());
                str.append("</td><td>");
                str.append(jobPojo.getCompleteddumps());
                str.append("</td><td>");
                if (jobPojo.getJobStatus().equals("Completed")) {
                    str.append("<span class='label label-success' >Completed</span>");
                } else if (jobPojo.getJobStatus().equals("Active")) {
                    str.append("<span class='label label-info' >Active</span>");
                } else if (jobPojo.getJobStatus().equals("Pending")) {
                    str.append("<span class='label label-danger'>Pending</span>");
                }
                str.append("</td><td>");
                str.append(jobPojo.getDrivercount());
                str.append("</td></tr>");
            }
        }
        response.addProperty("table", str.toString());
        return response.toString();
    }

    @RequestMapping(value = "/PrevJobDate/{jobDate}", method = RequestMethod.GET)
    public String PrevJobDate(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("jobDate") String jobDate) throws ParseException {

        DateUtils dateutil = new DateUtils();
        Date sourceDate = dateutil.stringToDate(jobDate, "MMMM dd, yyyy");
        Date myDate = dateutil.DecreaseDays(sourceDate, 1);
//        model.addAttribute("customer", cuService.count(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("job", joService.count(Constant.ACTIVE.toString()));
//        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), myDate));
//        model.addAttribute("countDumpingDone", joService.countDumpingDone(Constant.ACTIVE.toString(), myDate));

        model.addAttribute("job", joService.count(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("totalDump", joService.totalDumpCount(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("countDumpingPickup", joService.countDumpingPickup(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("countDumpingDone", joService.countDumpingDone(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("driver", drService.count(Constant.ACTIVE.toString(), myDate));
        model.addAttribute("customer", cuService.customercount(Constant.ACTIVE.toString(), myDate));

        List<JobPojo> maJobsesList = joService.getJobList(Constant.ACTIVE.toString(), myDate);

        model.addAttribute("jobDate", new DateUtils().dateWithFormat(myDate, "MMMM dd, yyyy"));
        model.addAttribute("maJobsesList", maJobsesList);
        return "Dashboard/Dashboard";
    }

    @RequestMapping(value = "/DumpsList/{date}", method = RequestMethod.GET)
    public String DumpsList(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("date") String date) throws ParseException {
        String flag = request.getParameter("flag");
        if (flag != null) {
            Date jobDate = new DateUtils().stringToDate(date, "MMMM dd, yyyy");
            List<JobPojo> jobPojo = null;
            if (flag.equals("start")) {
                jobPojo = joService.getStartJob(jobDate);
                model.addAttribute("start", "start");
            } else if (flag.equals("end")) {
                jobPojo = joService.getEndJob(jobDate);
                model.addAttribute("end", "end");
            }
            model.addAttribute("jobPojo", jobPojo);
        }
        return "Dashboard/DumpsList";
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
