/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.customerService;
import com.wmtrucking.utils.CommonUtils;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
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

@RequestMapping(value = "/customer")
@Scope("request")
@Controller
public class customerController {
    
    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    customerService cusService;
    
    @ModelAttribute(value = "customer")
    public void customer(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }
    
    @RequestMapping(value = "/customerList", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {
        
        List<MaCustomer> maCustomer = cusService.list(Constant.DETETED.toString());
        model.addAttribute("maCustomer", maCustomer);
        
        return "Customer/List";
    }
    
    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {
        
        return "Customer/Create";
    }
    
    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) {
        List<String> errors = new ArrayList<>();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNull(request, "fname", "First Name", errors);
        validateUtil.checkNull(request, "phone", "Phone number", errors);
        validateUtil.checkNull(request, "lname", "Last Name", errors);
        validateUtil.checkNull(request, "cmpname", "Company Name", errors);
        validateUtil.checkLength(errors, request, "fname", "First Name", 255, 0);
        validateUtil.checkLength(errors, request, "mname", "middle Name", 255, 0);
        validateUtil.checkLength(errors, request, "lname", "Last Name", 255, 0);
        validateUtil.checkLength(errors, request, "cmpname", "Company Name", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "email", "Email", 255, 0);
        validateUtil.checkLength(errors, request, "phone", "Phone", 255, 1);
        validateUtil.checkLength(errors, request, "status", "Status", 255, 0);
        // validateUtil.checkNull(request, "countryCode", "Country Code", errors);

        CommonUtils commonUtils = new CommonUtils();
        if (!commonUtils.validatePhoneNumber(request.getParameter("phone"))) {
            errors.add("Please enter proper Phone number ");
        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
//        if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
//            MaCustomer checkEmail = cusService.checkEmail(Constant.DETETED.toString(), request.getParameter("email"));
//            if (checkEmail != null) {
//                errors.add("Email is already exist");
//            }
//        }
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Customer/Create";
        }
        // MaCustomer checkMobile = cusService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("phone"), request.getParameter("countryCode"));
        MaCustomer checkMobile = cusService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("phone"));
        if (checkMobile != null) {
            errors.add("Mobile is already exist");
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Customer/Create";
        }
        
        MaCustomer maCustomer = new MaCustomer();
        maCustomer.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maCustomer.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maCustomer.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maCustomer.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));
        maCustomer.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
//        maCustomer.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
//        maCustomer.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maCustomer.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maCustomer.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maCustomer.setState(validateUtil.getStringValue(request.getParameter("state")));
//        maCustomer.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maCustomer.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maCustomer.setPhone(validateUtil.getStringValue(request.getParameter("phone")));
        //  maCustomer.setCountrycode(validateUtil.getStringValue(request.getParameter("countryCode")));
        maCustomer.setStatus(validateUtil.getStringValue(request.getParameter("status")));
        maCustomer.setCreateddate(new Date());
        //maCustomer.setStatus(Constant.ACTIVE.toString());

        cusService.save(maCustomer);
        return "redirect:/customer/customerList?m=c";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {
        
        MaCustomer maCustomer = cusService.findoneDelete(Constant.ACTIVE.toString(), Long.parseLong(id));
        
        if (maCustomer != null) {
            maCustomer.setStatus(Constant.DETETED.toString());
            cusService.save(maCustomer);
            return "redirect:/customer/customerList?m=d";
            
        }
        return "redirect:/customer/customerList?m=n";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        
        MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), id);
        
        if (maCustomer != null) {
            model.addAttribute("maCustomer", maCustomer);
            return "Customer/Edit";
        }
        model.addAttribute("message", "notFound");
        return "redirect:/customer/customerList";
    }
    
    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String postEdit(HttpServletRequest request, Model model) {
        MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), Long.parseLong(request.getParameter("id")));
        List<String> errors = new ArrayList<>();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNull(request, "fname", "First Name", errors);
        validateUtil.checkNull(request, "phone", "Phone number", errors);
        validateUtil.checkNull(request, "lname", "Last Name", errors);
        validateUtil.checkNull(request, "cmpname", "Company Name", errors);
        validateUtil.checkLength(errors, request, "fname", "First Name", 255, 0);
        validateUtil.checkLength(errors, request, "mname", "middle Name", 255, 0);
        validateUtil.checkLength(errors, request, "lname", "Last Name", 255, 0);
        validateUtil.checkLength(errors, request, "cmpname", "Company Name", 255, 0);
        validateUtil.checkLength(errors, request, "add1", "Address ", 255, 0);
//        validateUtil.checkLength(errors, request, "add2", "Address 2", 255, 0);
//        validateUtil.checkLength(errors, request, "add3", "Address 3", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "email", "Email", 255, 0);
        validateUtil.checkLength(errors, request, "phone", "Phone", 255, 1);
        validateUtil.checkLength(errors, request, "status", "Status", 255, 0);
        
        if (errors.size() > 0) {
            model.addAttribute("maCustomer", maCustomer);
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Customer/Edit";
        }
        CommonUtils commonUtils = new CommonUtils();
        if (!commonUtils.validatePhoneNumber(request.getParameter("phone"))) {
            errors.add("Please enter proper Phone number ");
        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
        if (!maCustomer.getStatus().equals(request.getParameter("status"))) {
            MaCustomer maCustomers = cusService.findoneEdit(maCustomer.getId());
            if (maCustomers == null) {
                errors.add(" This Customer is assigned in job. For Inactive, please first remove Customer from job.");
            }
        }

        //MaCustomer checkMobile = cusService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("phone"), request.getParameter("countryCode"));
        MaCustomer checkMobile = cusService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("phone"));
        if (checkMobile != null && !maCustomer.getPhone().equals(checkMobile.getPhone())) {
            errors.add("Mobile is already exist");
        }
        if (errors.size() > 0) {
            model.addAttribute("maCustomer", maCustomer);
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Customer/Edit";
        }
//        maCustomer.setStatus(validateUtil.getStringValue(request.getParameter("status")));
        maCustomer.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maCustomer.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maCustomer.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maCustomer.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));
        maCustomer.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
//        maCustomer.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
//        maCustomer.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maCustomer.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maCustomer.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maCustomer.setState(validateUtil.getStringValue(request.getParameter("state")));
//        maCustomer.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maCustomer.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maCustomer.setPhone(validateUtil.getStringValue(request.getParameter("phone")));
        //  maCustomer.setCountrycode(validateUtil.getStringValue(request.getParameter("countryCode")));
//        maCustomer.setStatus(Constant.ACTIVE.toString());

        cusService.save(maCustomer);
        return "redirect:/customer/customerList?m=e";
    }
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {
        
        MaCustomer maCustomer = cusService.findone(Constant.DETETED.toString(), id);
        
        if (maCustomer != null) {
            model.addAttribute("maCustomer", maCustomer);
            return "Customer/view";
        }
        model.addAttribute("message", "notFound");
        return "redirect:/customer/customerList";
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
