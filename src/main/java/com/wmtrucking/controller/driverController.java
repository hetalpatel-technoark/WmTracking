/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.google.gson.JsonObject;
import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.driverService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/driver")
@Scope("request")
@Controller
public class driverController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    driverService drService;

    @ModelAttribute(value = "driver")
    public void customer(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {

        // MaAuthobject iamObjects = (MaAuthobject) sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString());
        List<MaDriver> maDriver = drService.list(Constant.ACTIVE.toString());
        model.addAttribute("maDriver", maDriver);

        return "Driver/List";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {
        return "Driver/Create";
    }

    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) {
        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();
        validateUtil.checkNullAndLength(errors, request, "fname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "mname", 255, 1);
        validateUtil.checkNullAndLength(errors, request, "lname", 255, 1);
        validateUtil.checkLength(errors, request, "lno", 255, 0);
        validateUtil.checkLength(errors, request, "add1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", 255, 0);
        validateUtil.checkLength(errors, request, "city", 255, 0);
        validateUtil.checkLength(errors, request, "pin", 255, 0);
        validateUtil.checkLength(errors, request, "state", 255, 0);
        validateUtil.checkLength(errors, request, "country", 255, 0);
        validateUtil.checkLength(errors, request, "mob", 255, 0);
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Driver/Create";
        }
        MaDriver maDriver = new MaDriver();
        maDriver.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maDriver.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maDriver.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maDriver.setLicensenumber(validateUtil.getStringValue(request.getParameter("lno")));
        maDriver.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        maDriver.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        maDriver.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maDriver.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maDriver.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maDriver.setState(validateUtil.getStringValue(request.getParameter("state")));
        maDriver.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maDriver.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maDriver.setMobile(validateUtil.getStringValue(request.getParameter("mob")));
        maDriver.setStatus(Constant.ACTIVE.toString());

        drService.save(maDriver);
        return "redirect:/driver/List?m=c";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaDriver maDriver = drService.findoneDelete(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maDriver != null) {
            maDriver.setStatus(Constant.DETETED.toString());
            drService.save(maDriver);
            return "redirect:/driver/List?m=d";
        }
        return "redirect:/driver/List?m=n";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaDriver maDriver = drService.findone(Constant.ACTIVE.toString(), id);

        if (maDriver != null) {
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }
        return "redirect:/driver/List?m=n";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaDriver maDriver = drService.findone(Constant.ACTIVE.toString(), id);

        if (maDriver != null) {
            model.addAttribute("maDriver", maDriver);
            return "Driver/view";
        }
        return "redirect:/driver/List?m=n";
    }

    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String postEdit(HttpServletRequest request, Model model) {
        MaDriver maDriver = drService.findone(Constant.ACTIVE.toString(), Long.parseLong(request.getParameter("id")));
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
            model.addAttribute("maDriver", maDriver);
            return "Customer/Edit";
        }
        maDriver.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
        maDriver.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
        maDriver.setLastname(validateUtil.getStringValue(request.getParameter("lname")));
        maDriver.setLicensenumber(validateUtil.getStringValue(request.getParameter("lno")));
        maDriver.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
        maDriver.setAddress2(validateUtil.getStringValue(request.getParameter("add2")));
        maDriver.setAddress3(validateUtil.getStringValue(request.getParameter("add3")));
        maDriver.setCity(validateUtil.getStringValue(request.getParameter("city")));
        maDriver.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
        maDriver.setState(validateUtil.getStringValue(request.getParameter("state")));
        maDriver.setCountry(validateUtil.getStringValue(request.getParameter("country")));
        maDriver.setEmail(validateUtil.getStringValue(request.getParameter("email")));
        maDriver.setMobile(validateUtil.getStringValue(request.getParameter("mob")));
        maDriver.setStatus(Constant.ACTIVE.toString());

        drService.save(maDriver);
        return "redirect:/driver/List?m=e";
    }

}
