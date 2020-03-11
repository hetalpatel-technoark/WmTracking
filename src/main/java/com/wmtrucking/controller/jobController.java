/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.entities.MaAuthobject;
import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.entities.MaJobCustomer;
import com.wmtrucking.entities.MaJobDriver;
import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.pojo.DriverPojo;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.services.JobTransactionService;
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobCustomerService;
import com.wmtrucking.services.jobDriverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.APNPushUtil;
import com.wmtrucking.utils.CommonUtils;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.FirebaseNotification;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/job")
@Scope("request")
@Controller
public class jobController {

    @Autowired
    jobService jobService;
    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    customerService cusService;
    @Autowired
    driverService drService;
    @Autowired
    jobDriverService jobDriverService;
    @Autowired
    jobCustomerService jobcustomerService;
    @Autowired
    JobTransactionService jobTransactionService;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @ModelAttribute(value = "job")
    public void job(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String List(HttpServletRequest request, Model model) {
        //  List<MaJobs> maJobs = jobService.list(Constant.ACTIVE.toString());
        List<JobPojo> maJobs = jobService.getJobList(Constant.ACTIVE.toString(), Boolean.FALSE);
        model.addAttribute("maJobs", maJobs);

        return "Job/List";
    }

    @RequestMapping(value = "/archiveList", method = RequestMethod.GET)
    public String ArchiveList(HttpServletRequest request, Model model) {
        //  List<MaJobs> maJobs = jobService.list(Constant.ACTIVE.toString());
        List<JobPojo> maJobs = jobService.getJobList(Constant.ACTIVE.toString(), Boolean.TRUE);
        model.addAttribute("maJobs", maJobs);

        return "Job/ArchiveList";
    }

    @RequestMapping(value = "/getJobList", method = RequestMethod.POST)
    @ResponseBody
    public String getJobList(HttpServletRequest request, Model model) throws UnAthorizedUserException {

        List<JobPojo> maJobsesList = null;
        Boolean flag = Boolean.FALSE;
        if (request.getParameter("flag") != null && request.getParameter("flag").equals("archive")) {
            flag = Boolean.TRUE;
        }
        if (request.getParameter("searchtext") != null && !request.getParameter("searchtext").equals(" ")) {
            maJobsesList = jobService.searchJobList(Constant.ACTIVE.toString(), flag, request.getParameter("searchtext"));
        } else {
            maJobsesList = jobService.getJobList(Constant.ACTIVE.toString(), flag);
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
                str.append("</td><td>");
                if (jobPojo.getDrivercount() > 0) {
                    str.append("<div class='tooltip-wrap'><i class='feather icon-users'></i><div class='tooltip-content'><p>");
                    if (jobPojo.getDrivername() != null) {
                        String[] driver = jobPojo.getDrivername().split(",");
                        for (int i = 0; i < driver.length; i++) {
                            str.append("<span  class ='label label-orange '>");
                            str.append(driver[i]);
                            str.append(" </span>");
                        }
                    }
                    str.append("</p> </div> </div> ");
                }
                String view = "";
                if (flag) {
                    view = request.getContextPath() + "/job/view/" + jobPojo.getId() + "\"?flag=false";
                } else {
                    view = request.getContextPath() + "/job/view/" + jobPojo.getId() + "\"?flag=true";

                }

                str.append("</td > <td ><div class='btn-group'><div class='dropdown'><button class='btn btn-flat-primary dropdown-toggle mr-1 mb-1'"
                        + " type='button' id='dropdownMenuButton100' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
                        + " <i class='feather icon-menu'></i> </button><div class='dropdown-menu' aria-labelledby='dropdownMenuButton100'>"
                        + " <a class='dropdown-item'  style='font-size: 15px;' href='");
                str.append(view);
                str.append("'> <i class='feather icon-eye'></i><span>View</span></a>");

                if (flag) {
                    str.append("<a class='dropdown-item'  style='font-size: 15px;' href='");
                    str.append(request.getContextPath());
                    str.append("/invoice/list/");
                    str.append(jobPojo.getId());
                    str.append("'><i class='feather icon-eye '></i><span>Show Invoice</span></a> <a class='dropdown-item'  onclick='unarchive('Unarchive','");
                    str.append(jobPojo.getId());
                    str.append("')'><i class='feather icon-archive '></i> <span>Unarchive</span></a>");
                } else {
                    if (!jobPojo.getTransectionstatus().equals("0")) {
                        str.append(" <a class='dropdown-item'  style='font-size: 15px;' href='");
                        str.append(request.getContextPath());
                        str.append("/job/assignJobDr/");
                        str.append(jobPojo.getId());
                        str.append("'><i class='feather icon-user'></i><span>Assign Driver</span></a> ");

                        if (!jobPojo.getTransectionstatus().equals("3")) {
                            str.append(" <a class='dropdown-item' onclick='changeStatus(\"Delete\",\"");
                            str.append(jobPojo.getId());
                            str.append("\")'><i class='feather icon-trash'></i> <span>Delete</span></a>");
                        }
                    } else {

                        str.append("<a class='dropdown-item'  style='font-size: 15px;' href='");
                        str.append(request.getContextPath());
                        str.append("/invoice/list/");
                        str.append(jobPojo.getId());
                        str.append("'><i class='feather icon-eye '></i><span>Show Invoice</span></a> <a class='dropdown-item'  onclick='archive(\"Archive\", '");
                        str.append(jobPojo.getId());
                        str.append("')'><i class='feather icon-archive '></i> <span>Archive</span></a>");
                    }
                    str.append("<a class='dropdown-item' href='");
                    str.append(request.getContextPath());
                    str.append("/job/edit/");
                    str.append(jobPojo.getId());
                    str.append("'><i class='feather icon-edit'></i> <span>Edit</span></a>");
                }
                str.append("</div></div></div></td></tr>");
            }
        }
        response.addProperty("table", str.toString());
        return response.toString();
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {
        List<MaCustomer> maCustomer = cusService.activeList(Constant.ACTIVE.toString());
        model.addAttribute("maCustomer", maCustomer);
        List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
        model.addAttribute("maDriver", maDriver);
        return "Job/Create";
    }

    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) throws ParseException {
        //JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        List<String> errors = new ArrayList<>();

        validateUtil.checkNull(request, "jno", "Customer PO", errors);
        validateUtil.checkNull(request, "count", "Total Dumps", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);
        validateUtil.checkNull(request, "jname", "Job Name", errors);
        validateUtil.checkNull(request, "price", "Price", errors);
        validateUtil.checkLength(errors, request, "count", "Total Dumps", 255, 1);
        validateUtil.checkLength(errors, request, "jname", "Job Name", 255, 1);

        validateUtil.checkLength(errors, request, "others", "Others", 255, 0);
        validateUtil.checkNull(request, "DumpingAddress", "Dumping Address", errors);
        validateUtil.checkNull(request, "lodingAddress", "Loding Address", errors);

        if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {
            validateUtil.checkNull(request, "loding_lat", "loding latitude", errors);
            validateUtil.checkNull(request, "loding_log", "Loding logitude", errors);
            validateUtil.checkNull(request, "dumping_lat", "Dumping latitude", errors);
            validateUtil.checkNull(request, "dumping_log", "Dumping logitude", errors);
        } else {
            validateUtil.checkNull(request, "loding_lat_txt", "loding latitude", errors);
            validateUtil.checkNull(request, "loding_log_txt", "Loding logitude", errors);
            validateUtil.checkNull(request, "dumping_lat_txt", "Dumping latitude", errors);
            validateUtil.checkNull(request, "dumping_log_txt", "Dumping logitude", errors);
        }

        if (request.getParameter("count") != null & (Long.parseLong(request.getParameter("count")) <= 0)) {
            errors.add("Total Dumps Allow Only More then 0");
        }

        MaJobs checkjob = jobService.checkJobNumber(Constant.ACTIVE.toString(), request.getParameter("jno"));
        if (checkjob != null) {
            errors.add("This Customer PO is already exist");
        }

        //       CommonUtils commonUtils = new CommonUtils();
//        if (request.getParameter("jno") != null && !commonUtils.isBigInteger(request.getParameter("jno"))) {
//            errors.add("Please Enter Proper Job number");
//        }
        if (request.getParameter("lodingAddress") != null && request.getParameter("DumpingAddress") != null
                && request.getParameter("lodingAddress").equals(request.getParameter("DumpingAddress"))) {
            errors.add("Loading and Dumping  Site Address should not be same");
        }
        if (errors.size() > 0) {
            List<MaCustomer> maCustomer = cusService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Job/Create";
        }
        MaJobs majob = new MaJobs();
        if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {

//            if (!commonUtils.isBigDecimal(request.getParameter("loding_lat")) && !commonUtils.isBigDecimal(request.getParameter("loding_log"))
//                    && !commonUtils.isBigDecimal(request.getParameter("dumping_lat")) && !commonUtils.isBigDecimal(request.getParameter("dumping_log"))) {
//                List<MaCustomer> maCustomer = cusService.activeList(Constant.ACTIVE.toString());
//                model.addAttribute("maCustomer", maCustomer);
//                List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
//                model.addAttribute("maDriver", maDriver);
//                errors.add("Please Provide Proper Latitude and Logitude ");
//                model.addAttribute(Constant.ERRORPARAM.toString(), errors);
//                return "Job/Create";
//            }
            majob.setFromlatitude(validateUtil.getStringValue(request.getParameter("loding_lat")));
            majob.setFromlongitude(validateUtil.getStringValue(request.getParameter("loding_log")));
            majob.setTolatitude(validateUtil.getStringValue(request.getParameter("dumping_lat")));
            majob.setTolongitude(validateUtil.getStringValue(request.getParameter("dumping_log")));
        } else {
            majob.setFromlatitude(validateUtil.getStringValue(request.getParameter("loding_lat_txt")));
            majob.setFromlongitude(validateUtil.getStringValue(request.getParameter("loding_log_txt")));
            majob.setTolatitude(validateUtil.getStringValue(request.getParameter("dumping_lat_txt")));
            majob.setTolongitude(validateUtil.getStringValue(request.getParameter("dumping_log_txt")));
        }
        majob.setDumpingaddress(validateUtil.getStringValue(request.getParameter("DumpingAddress")));
        majob.setLodingaddress(validateUtil.getStringValue(request.getParameter("lodingAddress")));

        MaAuthobject maAuthobject = (MaAuthobject) sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString());

        majob.setHaulback(Boolean.parseBoolean((request.getParameter("haulBack"))));
        majob.setHauloff(Boolean.parseBoolean(request.getParameter("haulOff")));
        majob.setSand(Boolean.parseBoolean(request.getParameter("Sand")));
        majob.setCommon(Boolean.parseBoolean(request.getParameter("common")));
        majob.setHourly(Boolean.parseBoolean(request.getParameter("hourly")));
        majob.setSelectfill(Boolean.parseBoolean(request.getParameter("selectfill")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
        majob.setPrice(validateUtil.getStringValue(request.getParameter("price")));
        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setJobname(validateUtil.getStringValue(request.getParameter("jname")));
        majob.setStatus(Constant.ACTIVE.toString());
        majob.setOther(validateUtil.getStringValue(request.getParameter("others")));
        majob.setNotes(validateUtil.getStringValue(request.getParameter("notes")));
        majob.setCreateddate(new Date());
        majob.setCreatedby(maAuthobject);
        majob.setJob_status(Constant.PENDING.toString());
        //  majob.setTotaljobcount(validateUtil.getLongValue(request.getParameter("count")));
        majob.setTotaljobcount(Long.parseLong(request.getParameter("count")));
        majob.setIsarchive(Boolean.FALSE);
        jobService.save(majob);
        //Job assign for customer
        if (request.getParameterValues("customer") != null) {
            for (String customer : request.getParameterValues("customer")) {
                MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), Long.parseLong(customer));
                MaJobCustomer maJobCustomer = new MaJobCustomer();
                maJobCustomer.setJobId(majob);
                maJobCustomer.setCustomerId(maCustomer);
                jobcustomerService.save(maJobCustomer);
            }
        }

        return "redirect:/job/List?m=c";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        //MaJobs maJobs = jobService.findoneCompletedjob(Constant.ACTIVE.toString(), id);
        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), id, Boolean.FALSE);

        if (maJobs != null) {
            model.addAttribute("maJobs", maJobs);
            List<MaCustomer> maCustomer = cusService.activeListEdit(Constant.ACTIVE.toString(), id);
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);

            String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), id);
            model.addAttribute("maJobDrivers", maJobDrivers);

            String majobcustomer = jobcustomerService.list(Constant.ACTIVE.toString(), id);
            model.addAttribute("majobcustomer", majobcustomer);

            return "Job/Edit";
        }
        return "redirect:/job/List?m=completed";
    }

    @RequestMapping(value = "/PostEdit", method = RequestMethod.POST)
    public String PostEdit(HttpServletRequest request, Model model) {
        //  JsonObject errors = new JsonObject();
        List<String> errors = new ArrayList<>();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNull(request, "jno", "Customer PO", errors);
        validateUtil.checkNull(request, "count", "Total Dumps", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);
        validateUtil.checkNull(request, "jname", "Job Name", errors);
        validateUtil.checkNull(request, "price", "Price", errors);
        validateUtil.checkLength(errors, request, "jno", "Customer PO", 255, 0);
        validateUtil.checkLength(errors, request, "count", "Total Dumps", 255, 1);
        validateUtil.checkLength(errors, request, "jname", "Job Name", 255, 1);
        validateUtil.checkLength(errors, request, "others", "Others", 255, 0);
        validateUtil.checkNull(request, "DumpingAddress", "Dumping Address", errors);
        validateUtil.checkNull(request, "lodingAddress", "Loding Address", errors);
        if (request.getParameter("count") != null & (Long.parseLong(request.getParameter("count")) <= 0)) {
            errors.add("Total Dumps Allow More then 0");
        }
        if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {
            validateUtil.checkNull(request, "loding_lat", "Loding Latitude", errors);
            validateUtil.checkNull(request, "loding_log", "Loding Logitude", errors);
            validateUtil.checkNull(request, "dumping_lat", "Dumping Latitude", errors);
            validateUtil.checkNull(request, "dumping_log", "Dumping Logitude", errors);
        }
        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")), Boolean.FALSE);

        Long jobTransaction = jobTransactionService.totalJobTransactionCount(majob.getId());
        if (jobTransaction <= Long.parseLong(request.getParameter("count"))) {
            majob.setJob_status(Constant.PENDING.toString());
        } else if (jobTransaction >= Long.parseLong(request.getParameter("count"))) {
            errors.add("Your Transaction is already completed. So, You can't able to decrease Total Dumps");
        }

        MaJobs checkjob = jobService.checkJobNumber(Constant.ACTIVE.toString(), request.getParameter("jno"));
        if (checkjob != null && !majob.getJobnumber().equals(checkjob.getJobnumber())) {
            errors.add("This Customer PO is already exist");
        }
//        CommonUtils commonUtils = new CommonUtils();
//        if (request.getParameter("jno") != null && !commonUtils.isBigInteger(request.getParameter("jno"))) {
//            errors.add("Please Enter Proper Job number");
//        }
        if (request.getParameter("lodingAddress") != null && request.getParameter("DumpingAddress") != null
                && request.getParameter("lodingAddress").equals(request.getParameter("DumpingAddress"))) {
            errors.add("Loading and Dumping  Site Address should not be same");
        }

        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maJobs", majob);
            List<MaCustomer> maCustomer = cusService.activeListEdit(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
            model.addAttribute("maJobDrivers", maJobDrivers);
            String majobcustomer = jobcustomerService.list(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
            model.addAttribute("majobcustomer", majobcustomer);
             return "Job/Edit";
        }

        //HNot completed completed job
        // MaJobs majob = jobService.findoneCompletedjob(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
        if (majob != null) {
            majob.setHaulback(Boolean.parseBoolean(request.getParameter("haulBack")));
            majob.setHauloff(Boolean.parseBoolean(request.getParameter("haulOff")));
            majob.setSand(Boolean.parseBoolean(request.getParameter("Sand")));
            majob.setCommon(Boolean.parseBoolean(request.getParameter("common")));
            majob.setHourly(Boolean.parseBoolean(request.getParameter("hourly")));
            majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
            majob.setJobname(validateUtil.getStringValue(request.getParameter("jname")));
            majob.setPrice(validateUtil.getStringValue(request.getParameter("price")));
            majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
            majob.setOther(validateUtil.getStringValue(request.getParameter("others")));
            majob.setNotes(validateUtil.getStringValue(request.getParameter("notes")));
            majob.setTotaljobcount(validateUtil.getLongValue(request.getParameter("count")));

            if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {
                majob.setFromlatitude(validateUtil.getStringValue(request.getParameter("loding_lat")));
                majob.setFromlongitude(validateUtil.getStringValue(request.getParameter("loding_log")));
                majob.setTolatitude(validateUtil.getStringValue(request.getParameter("dumping_lat")));
                majob.setTolongitude(validateUtil.getStringValue(request.getParameter("dumping_log")));
            } else {
                if ((request.getParameter("loding_lat_txt") != null && !request.getParameter("loding_lat_txt").equals("")) && (request.getParameter("loding_log_txt") != null && !request.getParameter("loding_log_txt").equals(""))) {
                    majob.setFromlatitude(validateUtil.getStringValue(request.getParameter("loding_lat_txt")));
                    majob.setFromlongitude(validateUtil.getStringValue(request.getParameter("loding_log_txt")));
                }
                if ((request.getParameter("dumping_lat_txt") != null && !request.getParameter("dumping_lat_txt").equals("")) && (request.getParameter("dumping_log_txt") != null && !request.getParameter("dumping_log_txt").equals(""))) {
                    majob.setTolatitude(validateUtil.getStringValue(request.getParameter("dumping_lat_txt")));
                    majob.setTolongitude(validateUtil.getStringValue(request.getParameter("dumping_log_txt")));
                }
            }

            majob.setDumpingaddress(validateUtil.getStringValue(request.getParameter("DumpingAddress")));
            majob.setLodingaddress(validateUtil.getStringValue(request.getParameter("lodingAddress")));

            jobService.save(majob);

            jobcustomerService.deleteOldCustomerJob(Constant.ACTIVE.toString(), majob.getId());
            //Job assign for customer
            if (request.getParameterValues("customer") != null) {
                for (String customer : request.getParameterValues("customer")) {
                    MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), Long.parseLong(customer));
                    MaJobCustomer maJobCustomer = new MaJobCustomer();
                    maJobCustomer.setJobId(majob);
                    maJobCustomer.setCustomerId(maCustomer);
                    jobcustomerService.save(maJobCustomer);
                }
            }
            return "redirect:/job/List?m=edit";
        }
        return "redirect:/job/List?m=completed";

    }

    @RequestMapping(value = "/archive/{jobid}", method = RequestMethod.GET)
    public String Archive(HttpServletRequest request, Model model, @PathVariable("jobid") Long jobid) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), jobid, Boolean.FALSE);
        if (maJobs != null) {
            maJobs.setIsarchive(Boolean.TRUE);
            jobService.save(maJobs);
        }
        return "redirect:/job/List?m=archived";
    }

    @RequestMapping(value = "/unarchive/{jobid}", method = RequestMethod.GET)
    public String Unarchive(HttpServletRequest request, Model model, @PathVariable("jobid") Long jobid) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), jobid, Boolean.TRUE);
        if (maJobs != null) {
            maJobs.setIsarchive(Boolean.FALSE);
            jobService.save(maJobs);
        }
        return "redirect:/job/archiveList?m=unarchived";
    }

    @RequestMapping(value = "/assignJobDr/{jobid}", method = RequestMethod.GET)
    public String assignDriver(HttpServletRequest request, Model model, @PathVariable("jobid") Long jobid) {

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), jobid, Boolean.FALSE);
        /**
         * Selected driver *
         */
//        String selectedDriver = jobDriverService.list(Constant.ACTIVE.toString(), jobid);
        //      model.addAttribute("selectedDriver", selectedDriver);

        /**
         * List of driver *
         */
        //List<MaDriver> TotalDriver = drService.activeList(Constant.ACTIVE.toString());
        List<MaDriver> TotalDriver = drService.driverList(Constant.ACTIVE.toString(), jobid);
        model.addAttribute("TotalDriver", TotalDriver);

        /**
         * List of Selected driver *
         */
//        List<MaJobDriver> maJobDrivers = jobDriverService.listOfDriver(Constant.ACTIVE.toString(), jobid);
        List<DriverPojo> maJobDrivers = jobDriverService.getDriverList(jobid);

        model.addAttribute("maJobDrivers", maJobDrivers);

        model.addAttribute("maJob", majob);
        return "Job/AssignJobDr";
    }

    @RequestMapping(value = "/PostCreateAssignDrive", method = RequestMethod.POST)
    public String PostCreateAssignDrive(HttpServletRequest request, Model model) {
        ValidateUtil validateUtil = new ValidateUtil();
        List<String> errors = new ArrayList<>();
        validateUtil.checkNull(request, "driver", "Driver", errors);
        if (errors.size() > 0) {
            MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")), Boolean.FALSE);
            String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));
            model.addAttribute("maJobDrivers", maJobDrivers);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            model.addAttribute("maJob", majob);
            return "Job/AssignJobDr";
        }

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")), Boolean.FALSE);
        if (majob != null) {
            // List<MaJobDriver> maJobDriversold = jobDriverService.listOfDriver(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));
            if (request.getParameterValues("driver") != null) {
                for (String driver : request.getParameterValues("driver")) {
                    //  MaJobDriver mjd = jobDriverService.findDriver(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")), Long.parseLong(driver));
                    Date creDate = new Date();
//                    if (mjd != null) {
//                        creDate = mjd.getCreateddate();
//                    }
                    MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(driver));
                    MaJobDriver maJobDriver = new MaJobDriver();
                    maJobDriver.setJobId(majob);
                    maJobDriver.setDriverId(maDriver);
                    maJobDriver.setCreateddate(creDate);
                    jobDriverService.save(maJobDriver);

                    String ios = maDriver.getMaPushNotificationList().stream().filter(line -> "IOS".equalsIgnoreCase(line.getType()))
                            .collect(Collectors.toList()).stream().map(c -> c.getDevicetoken()).collect(Collectors.joining(","));
                    String android = maDriver.getMaPushNotificationList().stream().filter(line -> "Android".equalsIgnoreCase(line.getType()))
                            .collect(Collectors.toList()).stream().map(c -> c.getDevicetoken()).collect(Collectors.joining(","));

                    threadPoolTaskExecutor.execute(new APNPushUtil(ios, "You have assigned in job " + majob.getJobname()));
                    threadPoolTaskExecutor.execute(new FirebaseNotification(android, "You have assigned in job " + majob.getJobname()));

                }
            }

            // jobDriverService.delete(maJobDriversold);
            return "redirect:/job/List?m=assign";
        }
        return "redirect:/job/List?m=notAssign";
    }

    @RequestMapping(value = "/deleteAssignDriver/{id}/{jobid}", method = RequestMethod.GET)
    public String deleteAssignDriver(HttpServletRequest request, Model model, @PathVariable("id") Long id, @PathVariable("jobid") Long jobid) {

        MaJobDriver maJobDriver = jobDriverService.driverJob(id);

        if (maJobDriver != null) {
            jobDriverService.delete(maJobDriver);

            String ios = maJobDriver.getDriverId().getMaPushNotificationList().stream().filter(line -> "IOS".equalsIgnoreCase(line.getType()))
                    .collect(Collectors.toList()).stream().map(c -> c.getDevicetoken()).collect(Collectors.joining(","));
            String android = maJobDriver.getDriverId().getMaPushNotificationList().stream().filter(line -> "Android".equalsIgnoreCase(line.getType()))
                    .collect(Collectors.toList()).stream().map(c -> c.getDevicetoken()).collect(Collectors.joining(","));

            threadPoolTaskExecutor.execute(new APNPushUtil(ios, "You have Removed in job " + maJobDriver.getJobId().getJobname()));
            threadPoolTaskExecutor.execute(new FirebaseNotification(android, "You have Removed in job " + maJobDriver.getJobId().getJobname()));
            return "redirect:/job/assignJobDr/" + jobid + "?m=remove";
        }
        return "redirect:/job/assignJobDr/" + jobid + "?m=notremove";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        //MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(id));
        MaJobs maJobs = jobService.findPendingJob(Constant.ACTIVE.toString(), Long.parseLong(id), Boolean.FALSE);
        if (maJobs != null) {
            jobDriverService.deleteOldDriverJob(Constant.ACTIVE.toString(), maJobs.getId());
            maJobs.setStatus(Constant.DETETED.toString());
            jobService.save(maJobs);
            return "redirect:/job/List?m=delete";
        }
        return "redirect:/job/List?m=notDelete";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        boolean flag = Boolean.TRUE;
        if (request.getParameter("flag") != null && request.getParameter("flag").equals("false")) {
            flag = Boolean.FALSE;
        }
        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), id, flag);

        if (majob != null) {
            model.addAttribute("maJobs", majob);
            List<MaCustomer> maCustomer = cusService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), id);
            model.addAttribute("maJobDrivers", maJobDrivers);
            String majobcustomer = jobcustomerService.list(Constant.ACTIVE.toString(), id);
            model.addAttribute("majobcustomer", majobcustomer);
            // model.addAttribute("flag", flag);

            return "Job/view";
        }
        return "redirect:/job/List?m=n";
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
