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
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobCustomerService;
import com.wmtrucking.services.jobDriverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
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

    @ModelAttribute(value = "job")
    public void job(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {
        //  List<MaJobs> maJobs = jobService.list(Constant.ACTIVE.toString());
        List<Object[]> maJobs = jobService.list(Constant.ACTIVE.toString(), Constant.ENDED.toString());
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
    public String PostCreate(HttpServletRequest request, Model model) {
        //JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        List<String> errors = new ArrayList<>();

        validateUtil.checkNull(request, "jno", "Job Number", errors);
        validateUtil.checkNull(request, "customer", "Customer", errors);
        //validateUtil.checkNull(request, "driver", "Driver", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);
        validateUtil.checkNull(request, "DumpingAddress", "Dumping Address", errors);
        validateUtil.checkNull(request, "lodingAddress", "Loding Address", errors);

        validateUtil.checkNull(request, "loding_lat_txt", "loding latitude", errors);
        validateUtil.checkNull(request, "loding_log_txt", "Loding logitude", errors);
        validateUtil.checkNull(request, "dumping_lat_txt", "Dumping latitude", errors);
        validateUtil.checkNull(request, "dumping_log_txt", "Dumping logitude", errors);
//        validateUtil.checkLength(errors, request, "job_assigndate", "Job Assign date", 255, 0);
        validateUtil.checkLength(errors, request, "jno", "Job Number", 255, 0);
        validateUtil.checkLength(errors, request, "count", "Total Count", 255, 1);
        validateUtil.checkLength(errors, request, "jname", "Job Name", 255, 0);
        validateUtil.checkLength(errors, request, "add1", "Address 1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", "Address 2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", "Address 3", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "others", "Others", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);

        if (request.getParameter("count") != null & request.getParameter("count").equals("0")) {
            errors.add("count" + " " + "Allow More then 0");
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

        MaAuthobject maAuthobject = (MaAuthobject) sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString());

        majob.setHaulback(Boolean.parseBoolean((request.getParameter("haulBack"))));
        majob.setHauloff(Boolean.parseBoolean(request.getParameter("haulOff")));
        majob.setSand(Boolean.parseBoolean(request.getParameter("Sand")));
        majob.setCommon_hourly(Boolean.parseBoolean(request.getParameter("common_hourly")));
        majob.setSelectfill(Boolean.parseBoolean(request.getParameter("selectfill")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
//        majob.setJob_assignddate(validateUtil.getDateValue(request.getParameter("job_assigndate")));
        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setJobname(validateUtil.getStringValue(request.getParameter("jname")));
        majob.setStatus(Constant.ACTIVE.toString());
        majob.setOther(validateUtil.getStringValue(request.getParameter("others")));
        majob.setNotes(validateUtil.getStringValue(request.getParameter("notes")));
        majob.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        majob.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        majob.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        majob.setCity(validateUtil.getStringValue(request.getParameter("city")));
        majob.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        majob.setState(validateUtil.getStringValue(request.getParameter("state")));
        majob.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        majob.setCreateddate(new Date());
        majob.setCreatedby(maAuthobject);
        majob.setJob_status(Constant.PENDING.toString());
        majob.setTotaljobcount(validateUtil.getLongValue(request.getParameter("count")));
        majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat_txt")));
        majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log_txt")));
        majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat_txt")));
        majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log_txt")));
        majob.setDumpingaddress(validateUtil.getStringValue(request.getParameter("DumpingAddress")));
        majob.setLodingaddress(validateUtil.getStringValue(request.getParameter("lodingAddress")));

        jobService.save(majob);

//        //Job assign for driver
//        if (request.getParameterValues("driver") != null) {
//            for (String driver : request.getParameterValues("driver")) {
//                MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(driver));
//                MaJobDriver maJobDriver = new MaJobDriver();
//                maJobDriver.setJobId(majob);
//                maJobDriver.setDriverId(maDriver);
//                jobDriverService.save(maJobDriver);
//            }
//        }
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

        String maJobDrivers = jobDriverService.list(Constant.ACTIVE.toString(), jobid);
        model.addAttribute("maJobDrivers", maJobDrivers);

        List<MaDriver> maDriver = drService.activeList(Constant.ACTIVE.toString());
        model.addAttribute("maDriver", maDriver);
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
        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("jobid")));

        jobDriverService.deleteOldDriverJob(Constant.ACTIVE.toString(), majob.getId());

        //Job assign for driver
        if (request.getParameterValues("driver") != null) {
            for (String driver : request.getParameterValues("driver")) {
                MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(driver));
                MaJobDriver maJobDriver = new MaJobDriver();
                maJobDriver.setJobId(majob);
                maJobDriver.setDriverId(maDriver);
                jobDriverService.save(maJobDriver);
            }
        }

        return "redirect:/job/List?m=a";
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
    @RequestMapping(value = "/searchAddressDilivery/{id}", method = RequestMethod.GET)
    public String searchAddressDilivery(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), id);
        if (majob != null) {
            model.addAttribute("majob", majob);
        }
        return "Job/DiliveryAddress";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maJobs != null) {
            maJobs.setStatus(Constant.DETETED.toString());
            jobService.save(maJobs);
        }
        return "redirect:/job/List?m=d";
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
        validateUtil.checkNull(request, "customer", "Customer", errors);
        // validateUtil.checkNull(request, "driver", "Driver", errors);
        validateUtil.checkNull(request, "jobdate", "Job Date", errors);

//        validateUtil.checkLength(errors, request, "job_assigndate", "Job Assign date", 255, 0);
        validateUtil.checkLength(errors, request, "jno", "Job Number", 255, 0);
        validateUtil.checkLength(errors, request, "jname", "Job Name", 255, 0);

        validateUtil.checkLength(errors, request, "add1", "Address 1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", "Address 2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", "Address 3", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "others", "Others", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));

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
        majob.setCommon_hourly(Boolean.parseBoolean(request.getParameter("common_hourly")));
        majob.setSelectfill(Boolean.parseBoolean(request.getParameter("selectfill")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
        majob.setJobname(validateUtil.getStringValue(request.getParameter("jname")));

//        majob.setJob_assignddate(validateUtil.getDateValue(request.getParameter("job_assigndate")));
        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setStatus(Constant.ACTIVE.toString());

//        majob.setSelectfill(validateUtil.getStringValue(request.getParameter("selectfill")));
        majob.setOther(validateUtil.getStringValue(request.getParameter("others")));
        majob.setNotes(validateUtil.getStringValue(request.getParameter("notes")));

        majob.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        majob.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        majob.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        majob.setCity(validateUtil.getStringValue(request.getParameter("city")));
        majob.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        majob.setState(validateUtil.getStringValue(request.getParameter("state")));
        majob.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        majob.setTotaljobcount(validateUtil.getLongValue(request.getParameter("count")));

        if ((request.getParameter("loding_lat_txt") != null && !request.getParameter("loding_lat_txt").equals("")) && (request.getParameter("loding_log_txt") != null && !request.getParameter("loding_log_txt").equals(""))) {
            majob.setFromlatitude(new BigDecimal(request.getParameter("loding_lat_txt")));
            majob.setFromlongitude(new BigDecimal(request.getParameter("loding_log_txt")));
        }
        if ((request.getParameter("dumping_lat_txt") != null && !request.getParameter("dumping_lat_txt").equals("")) && (request.getParameter("dumping_log_txt") != null && !request.getParameter("dumping_log_txt").equals(""))) {
            majob.setTolatitude(new BigDecimal(request.getParameter("dumping_lat_txt")));
            majob.setTolongitude(new BigDecimal(request.getParameter("dumping_log_txt")));
        }
        majob.setDumpingaddress(validateUtil.getStringValue(request.getParameter("DumpingAddress")));
        majob.setLodingaddress(validateUtil.getStringValue(request.getParameter("lodingAddress")));

        jobService.save(majob);

//        jobDriverService.deleteOldDriverJob(Constant.ACTIVE.toString(), majob.getId());
//        if (request.getParameterValues("driver") != null) {
//            for (String driver : request.getParameterValues("driver")) {
//                MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(driver));
//                MaJobDriver maJobDriver = new MaJobDriver();
//                maJobDriver.setJobId(majob);
//                maJobDriver.setDriverId(maDriver);
//                jobDriverService.save(maJobDriver);
//            }
//        }
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
        return "redirect:/job/List?m=e";
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
