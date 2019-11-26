/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.entities.MaCustomer;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.customerService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
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

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {

        // MaAuthobject iamObjects = (MaAuthobject) sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString());
        List<MaCustomer> maCustomer = cusService.list(Constant.ACTIVE.toString());
        model.addAttribute("maCustomer", maCustomer);

        return "Customer/List";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {

        return "Customer/Create";
    }

    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) {
        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNullAndLength(errors, request, "fname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "mname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "lname", 255, 1);
        validateUtil.checkLength(errors, request, "cmpname", 255, 0);
        validateUtil.checkLength(errors, request, "add1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", 255, 0);
        validateUtil.checkLength(errors, request, "city", 255, 0);
        validateUtil.checkLength(errors, request, "pin", 255, 0);
        validateUtil.checkLength(errors, request, "state", 255, 0);
        validateUtil.checkLength(errors, request, "country", 255, 0);
        validateUtil.checkLength(errors, request, "phone", 255, 0);
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Customer/Create";
        }
        MaCustomer maCustomer = new MaCustomer();
        maCustomer.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maCustomer.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maCustomer.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maCustomer.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));
        maCustomer.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        maCustomer.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        maCustomer.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maCustomer.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maCustomer.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maCustomer.setState(validateUtil.getStringValue(request.getParameter("state")));
        maCustomer.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maCustomer.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maCustomer.setPhone(validateUtil.getStringValue(request.getParameter("phone")));
        maCustomer.setStatus(Constant.ACTIVE.toString());

        cusService.save(maCustomer);
        return "redirect:/customer/List?m=c";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaCustomer maCustomer = cusService.findoneDelete(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maCustomer != null) {
            maCustomer.setStatus(Constant.DETETED.toString());
            cusService.save(maCustomer);
            return "redirect:/customer/List?m=d";

        }
        return "redirect:/customer/List?m=n";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaCustomer maCustomer = cusService.findone(Constant.ACTIVE.toString(), id);

        if (maCustomer != null) {
            model.addAttribute("maCustomer", maCustomer);
            return "Customer/Edit";
        }
        model.addAttribute("message", "notFound");
        return "redirect:/customer/List";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaCustomer maCustomer = cusService.findone(Constant.ACTIVE.toString(), id);

        if (maCustomer != null) {
            model.addAttribute("maCustomer", maCustomer);
            return "Customer/view";
        }
        model.addAttribute("message", "notFound");
        return "redirect:/customer/List";
    }

    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String postEdit(HttpServletRequest request, Model model) {
        MaCustomer maCustomer = cusService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNullAndLength(errors, request, "fname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "mname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "lname", 255, 1);
        validateUtil.checkLength(errors, request, "cmpname", 255, 0);
        validateUtil.checkLength(errors, request, "add1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", 255, 0);
        validateUtil.checkLength(errors, request, "city", 255, 0);
        validateUtil.checkLength(errors, request, "pin", 255, 0);
        validateUtil.checkLength(errors, request, "state", 255, 0);
        validateUtil.checkLength(errors, request, "country", 255, 0);
        validateUtil.checkLength(errors, request, "phone", 255, 0);
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maCustomer", maCustomer);
            return "Customer/Edit";
        }
        maCustomer.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maCustomer.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maCustomer.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maCustomer.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));
        maCustomer.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        maCustomer.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        maCustomer.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maCustomer.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maCustomer.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maCustomer.setState(validateUtil.getStringValue(request.getParameter("state")));
        maCustomer.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maCustomer.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maCustomer.setPhone(validateUtil.getStringValue(request.getParameter("phone")));
        maCustomer.setStatus(Constant.ACTIVE.toString());

        cusService.save(maCustomer);
        return "redirect:/customer/List?m=e";
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