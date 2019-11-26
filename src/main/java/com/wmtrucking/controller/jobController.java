/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.entities.MaJobs;
import com.wmtrucking.services.customerService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.services.jobService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {
        List<MaJobs> maJobs = jobService.list(Constant.ACTIVE.toString());
        model.addAttribute("maJobs", maJobs);

        return "Job/List";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {
        List<MaCustomer> maCustomer = cusService.list(Constant.ACTIVE.toString());
        model.addAttribute("maCustomer", maCustomer);
        List<MaDriver> maDriver = drService.list(Constant.ACTIVE.toString());
        model.addAttribute("maDriver", maDriver);
        return "Job/Create";
    }

    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) {
        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNullAndLength(errors, request, "jno", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "customer", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "driver", 255, 1);
        validateUtil.checkLength(errors, request, "jobdate", 255, 0);
        validateUtil.checkLength(errors, request, "haulOff", 255, 0);
        validateUtil.checkLength(errors, request, "haulBack", 255, 0);
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Job/Create";
        }
        MaJobs majob = new MaJobs();
        MaCustomer maCustomer = cusService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("customer")));
        if (maCustomer != null) {
            majob.setCustId(maCustomer);
        }
        MaDriver maDriver = drService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("driver")));
        if (maDriver != null) {
            majob.setDriverId(maDriver);
        }
        majob.setHaulback(validateUtil.getStringValue(request.getParameter("haulBack")));
        majob.setHauloff(validateUtil.getStringValue(request.getParameter("haulOff")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setStatus(Constant.ACTIVE.toString());
        jobService.save(majob);
        return "redirect:/job/List?m=c";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maJobs != null) {
            maJobs.setStatus(Constant.DETETED.toString());
            jobService.save(maJobs);
        }
        return "redirect:/driver/List?m=d";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaJobs maJobs = jobService.findone(Constant.ACTIVE.toString(), id);

        if (maJobs != null) {
            model.addAttribute("maJobs", maJobs);
            List<MaCustomer> maCustomer = cusService.list(Constant.ACTIVE.toString());
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.list(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            return "Job/Edit";
        }
        return "redirect:/job/List?m=n";
    }

    @RequestMapping(value = "/PostEdit", method = RequestMethod.POST)
    public String PostEdit(HttpServletRequest request, Model model) {
        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNullAndLength(errors, request, "jno", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "customer", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "driver", 255, 1);
        validateUtil.checkLength(errors, request, "jobdate", 255, 0);
        validateUtil.checkLength(errors, request, "haulOff", 255, 0);
        validateUtil.checkLength(errors, request, "haulBack", 255, 0);
        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));

        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maJobs", majob);
            List<MaCustomer> maCustomer = cusService.list(Constant.ACTIVE.toString());
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.list(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            return "Job/Edit";
        }
        MaCustomer maCustomer = cusService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("customer")));
        if (maCustomer != null) {
            majob.setCustId(maCustomer);
        }
        MaDriver maDriver = drService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("driver")));
        if (maDriver != null) {
            majob.setDriverId(maDriver);
        }
        majob.setHaulback(validateUtil.getStringValue(request.getParameter("haulBack")));
        majob.setHauloff(validateUtil.getStringValue(request.getParameter("haulOff")));
        majob.setJobdate(validateUtil.getDateValue(request.getParameter("jobdate")));
        majob.setJobnumber(validateUtil.getStringValue(request.getParameter("jno")));
        majob.setStatus(Constant.ACTIVE.toString());
        jobService.save(majob);
        return "redirect:/job/List?m=e";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaJobs majob = jobService.findone(Constant.ACTIVE.toString(), id);

        if (majob != null) {
            model.addAttribute("maJobs", majob);
            List<MaCustomer> maCustomer = cusService.list(Constant.ACTIVE.toString());
            model.addAttribute("maCustomer", maCustomer);
            List<MaDriver> maDriver = drService.list(Constant.ACTIVE.toString());
            model.addAttribute("maDriver", maDriver);
            return "Job/view";
        }
        return "redirect:/jov/List?m=n";
    }

}
