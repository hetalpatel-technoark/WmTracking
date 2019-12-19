/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaAuthobject;
import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.entities.MaJobCustomer;
import com.wmtrucking.entities.MaJobDriver;
import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.pojo.JobPojo;
import com.wmtrucking.services.JobTransactionService;
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobCustomerService;
import com.wmtrucking.services.jobDriverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.CommonUtils;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @ModelAttribute(value = "job")
    public void job(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {
        //  List<MaJobs> maJobs = jobService.list(Constant.ACTIVE.toString());
        List<JobPojo> maJobs = jobService.getJobList(Constant.ACTIVE.toString());
        model.addAttribute("maJobs", maJobs);

        return "Job/List";
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

        validateUtil.checkNull(request, "jno", "Job Number", errors);
        validateUtil.checkNull(request, "count", "Total Dumps", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);
        validateUtil.checkNull(request, "jname", "Job Name", errors);
        validateUtil.checkNull(request, "price", "Price", errors);
        validateUtil.checkLength(errors, request, "jno", "Job Number", 255, 0);
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

        if (request.getParameter("count") != null & request.getParameter("count").equals("0")) {
            errors.add("Total Dumps Allow More then 0");
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

            CommonUtils commonUtils = new CommonUtils();

            if (!commonUtils.isBigDecimal(request.getParameter("loding_lat")) && !commonUtils.isBigDecimal(request.getParameter("loding_log"))
                    && !commonUtils.isBigDecimal(request.getParameter("dumping_lat")) && !commonUtils.isBigDecimal(request.getParameter("dumping_log"))) {
                List<MaCustomer> maCustomer = cusService.activeList(Constant.ACTIVE.toString());
                model.addAttribute("maCustomer", maCustomer);
                List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
                model.addAttribute("maDriver", maDriver);
                errors.add("Please Provide Proper Latitude and Logitude ");
                model.addAttribute(Constant.ERRORPARAM.toString(), errors);
                return "Job/Create";
            }

            majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat")));
            majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log")));
            majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat")));
            majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log")));
        } else {
            majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat_txt")));
            majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log_txt")));
            majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat_txt")));
            majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log_txt")));
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

    @RequestMapping(value = "/assignJobDr/{jobid}", method = RequestMethod.GET)
    public String assignDriver(HttpServletRequest request, Model model, @PathVariable("jobid") Long jobid) {

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), jobid);
        /**
         * Selected driver *
         */
        String selectedDriver = jobDriverService.list(Constant.ACTIVE.toString(), jobid);
        model.addAttribute("selectedDriver", selectedDriver);

        /**
         * List of driver *
         */
        List<MaDriver> TotalDriver = drService.activeList(Constant.ACTIVE.toString());
        model.addAttribute("TotalDriver", TotalDriver);

        /**
         * List of Selected driver *
         */
        List<MaJobDriver> maJobDrivers = jobDriverService.listOfDriver(Constant.ACTIVE.toString(), jobid);
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
            MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));
            String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));
            model.addAttribute("maJobDrivers", maJobDrivers);
            List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            model.addAttribute("maJob", majob);
            return "Job/AssignJobDr";
        }
        MaJobs majob = jobService.findPendingJob(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));

        if (majob != null) {
            List<MaJobDriver> maJobDriversold = jobDriverService.listOfDriver(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));
            if (request.getParameterValues("driver") != null) {
                for (String driver : request.getParameterValues("driver")) {
                    MaJobDriver mjd = jobDriverService.findDriver(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")), Long.parseLong(driver));
                    Date creDate = new Date();
                    if (mjd != null) {
                        creDate = mjd.getCreateddate();
                    }
                    MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(driver));
                    MaJobDriver maJobDriver = new MaJobDriver();
                    maJobDriver.setJobId(majob);
                    maJobDriver.setDriverId(maDriver);
                    maJobDriver.setCreateddate(creDate);
                    jobDriverService.save(maJobDriver);
                }
            }
            jobDriverService.delete(maJobDriversold);
            return "redirect:/job/List?m=assign";
        }
        return "redirect:/job/List?m=notAssign";
    }

//    @RequestMapping(value = "/searchAddress/{id}", method = RequestMethod.GET)
//    public String searchAddress(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
//        
//        MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), id);
//        if (maCustomer != null) {
//            model.addAttribute("maCustomer", maCustomer);
//        }
//        return "Job/Address";
//    }
//    @RequestMapping(value = "/searchAddressDilivery/{id}", method = RequestMethod.GET)
//    public String searchAddressDilivery(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
//
//        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), id);
//        if (majob != null) {
//            model.addAttribute("majob", majob);
//        }
//        return "Job/DiliveryAddress";
//    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        //MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(id));
        MaJobs maJobs = jobService.findPendingJob(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maJobs != null) {
            jobDriverService.deleteOldDriverJob(Constant.ACTIVE.toString(), maJobs.getId());
            maJobs.setStatus(Constant.DETETED.toString());
            jobService.save(maJobs);
            return "redirect:/job/List?m=delete";
        }
        return "redirect:/job/List?m=notDelete";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), id);

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
        return "redirect:/job/List?m=n";
    }

    @RequestMapping(value = "/PostEdit", method = RequestMethod.POST)
    public String PostEdit(HttpServletRequest request, Model model) {
        //  JsonObject errors = new JsonObject();
        List<String> errors = new ArrayList<>();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNull(request, "jno", "Job Number", errors);
        validateUtil.checkNull(request, "count", "Total Dumps", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);
        validateUtil.checkNull(request, "jname", "Job Name", errors);
        validateUtil.checkNull(request, "price", "Price", errors);
        validateUtil.checkLength(errors, request, "jno", "Job Number", 255, 0);
        validateUtil.checkLength(errors, request, "count", "Total Dumps", 255, 1);
        validateUtil.checkLength(errors, request, "jname", "Job Name", 255, 1);
        validateUtil.checkLength(errors, request, "others", "Others", 255, 0);
        validateUtil.checkNull(request, "DumpingAddress", "Dumping Address", errors);
        validateUtil.checkNull(request, "lodingAddress", "Loding Address", errors);
        if (request.getParameter("count") != null & request.getParameter("count").equals("0")) {
            errors.add("Total Dumps Allow More then 0");
        }
        if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {
            validateUtil.checkNull(request, "loding_lat", "loding latitude", errors);
            validateUtil.checkNull(request, "loding_log", "Loding logitude", errors);
            validateUtil.checkNull(request, "dumping_lat", "Dumping latitude", errors);
            validateUtil.checkNull(request, "dumping_log", "Dumping logitude", errors);
        }
        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
        Long jobTransaction = jobTransactionService.totalJobTransactionCount(majob.getId());
        if (jobTransaction <= Long.parseLong(request.getParameter("count"))) {
            majob.setJob_status(Constant.PENDING.toString());
        } else if (jobTransaction >= Long.parseLong(request.getParameter("count"))) {
            errors.add("Your Transaction is already completed. So, You can't able to decrease Total Dumps");
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

        majob.setHaulback(Boolean.parseBoolean(request.getParameter("haulBack")));
        majob.setHauloff(Boolean.parseBoolean(request.getParameter("haulOff")));
        majob.setSand(Boolean.parseBoolean(request.getParameter("Sand")));
        majob.setCommon(Boolean.parseBoolean(request.getParameter("common")));
        majob.setHourly(Boolean.parseBoolean(request.getParameter("hourly")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
        majob.setJobname(validateUtil.getStringValue(request.getParameter("jname")));

        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setOther(validateUtil.getStringValue(request.getParameter("others")));
        majob.setNotes(validateUtil.getStringValue(request.getParameter("notes")));
        majob.setTotaljobcount(validateUtil.getLongValue(request.getParameter("count")));

        if (request.getParameter("lat_log") != null && !request.getParameter("lat_log").equals("") && request.getParameter("lat_log").equals("on")) {
            majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat")));
            majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log")));
            majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat")));
            majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log")));
        } else {
            if ((request.getParameter("loding_lat_txt") != null && !request.getParameter("loding_lat_txt").equals("")) && (request.getParameter("loding_log_txt") != null && !request.getParameter("loding_log_txt").equals(""))) {
                majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat_txt")));
                majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log_txt")));
            }
            if ((request.getParameter("dumping_lat_txt") != null && !request.getParameter("dumping_lat_txt").equals("")) && (request.getParameter("dumping_log_txt") != null && !request.getParameter("dumping_log_txt").equals(""))) {
                majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat_txt")));
                majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log_txt")));
            }
        }
//        if ((request.getParameter("loding_lat_txt") != null && !request.getParameter("loding_lat_txt").equals("")) && (request.getParameter("loding_log_txt") != null && !request.getParameter("loding_log_txt").equals(""))) {
//            majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat_txt")));
//            majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log_txt")));
//        }
//        if ((request.getParameter("dumping_lat_txt") != null && !request.getParameter("dumping_lat_txt").equals("")) && (request.getParameter("dumping_log_txt") != null && !request.getParameter("dumping_log_txt").equals(""))) {
//            majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat_txt")));
//            majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log_txt")));
//        }
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

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), id);

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

            return "Job/view";
        }
        return "redirect:/jov/List?m=n";
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
