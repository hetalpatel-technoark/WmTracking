/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.driverService;
import com.wmtrucking.utils.CommonUtils;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.SessionUtils;
import com.wmtrucking.utils.ValidateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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

@RequestMapping(value = "/driver")
@Scope("request")
@Controller
public class driverController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    driverService drService;

    @ModelAttribute(value = "driver")
    public void driver(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/drivelist", method = RequestMethod.GET)
    public String createnote(HttpServletRequest request, Model model) {

        // MaAuthobject iamObjects = (MaAuthobject) sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString());
        List<MaDriver> maDriver = drService.list(Constant.DETETED.toString());
        model.addAttribute("maDriver", maDriver);

        return "Driver/List";
    }

    @RequestMapping(value = "/Create", method = RequestMethod.GET)
    public String Create(HttpServletRequest request, Model model) {
        return "Driver/Create";
    }

    @RequestMapping(value = "/PostCreate", method = RequestMethod.POST)
    public String PostCreate(HttpServletRequest request, Model model) {
//        JsonObject errors = new JsonObject();
        ValidateUtil validateUtil = new ValidateUtil();

        List<String> errors = new ArrayList<>();
        validateUtil.checkNull(request, "fname", "Name", errors);
        validateUtil.checkNull(request, "mob", "Mobile number", errors);
//        validateUtil.checkNull(request, "email", "Email", errors);
        //  validateUtil.checkLength(errors, request, "email", "Email", 255, 0);
        validateUtil.checkLength(errors, request, "fname", "First Name", 255, 1);
        validateUtil.checkLength(errors, request, "mname", "middle Name", 255, 0);
//        validateUtil.checkLength(errors, request, "lname", "Last Name", 255, 0);
        validateUtil.checkNull(request, "lname", "Last Name", errors);

        validateUtil.checkNull(request, "lno", "Licence number", errors);
//        validateUtil.checkNull(errors, request, "lno", "Licence number", 255, 1);
        validateUtil.checkLength(errors, request, "add1", "Address 1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", "Address 2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", "Address 3", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "email", "Email", 255, 0);
        validateUtil.checkLength(errors, request, "mob", "Mobile", 255, 1);
        validateUtil.checkLength(errors, request, "status", "Status", 255, 0);

        CommonUtils commonUtils = new CommonUtils();
        if (!commonUtils.validatePhoneNumber(request.getParameter("mob"))) {
            errors.add("Please enter proper Phone number ");
        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
//        if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
//            MaDriver checkEmail = drService.checkEmail(Constant.ACTIVE.toString(), request.getParameter("email"));
//            if (checkEmail != null) {
//                errors.add("Email is already exist");
//            }
//        }

        MaDriver checkMobile = drService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("mob"));
        if (checkMobile != null) {
            errors.add("Mobile is already exist");
        }

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
        //maDriver.setStatus(Constant.ACTIVE.toString());
        maDriver.setStatus(validateUtil.getStringValue(request.getParameter("status")));

        drService.save(maDriver);
        return "redirect:/driver/drivelist?m=c";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaDriver maDriver = drService.findoneDelete(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maDriver != null) {
            maDriver.setStatus(Constant.DETETED.toString());
            drService.save(maDriver);
            return "redirect:/driver/drivelist?m=d";
        }
        return "redirect:/driver/drivelist?m=n";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaDriver maDriver = drService.findone(Constant.DETETED.toString(), id);

        if (maDriver != null) {
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }
        return "redirect:/driver/drivelist?m=n";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(HttpServletRequest request, Model model, @PathVariable("id") Long id) {

        MaDriver maDriver = drService.findone(Constant.DETETED.toString(), id);

        if (maDriver != null) {
            model.addAttribute("maDriver", maDriver);
            return "Driver/view";
        }
        return "redirect:/driver/drivelist?m=n";
    }

    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String postEdit(HttpServletRequest request, Model model) {
        MaDriver maDriver = drService.findone(Constant.DETETED.toString(), Long.parseLong(request.getParameter("id")));
        ValidateUtil validateUtil = new ValidateUtil();

        List<String> errors = new ArrayList<>();
        validateUtil.checkNull(request, "fname", "Name", errors);
        validateUtil.checkNull(request, "mob", "Mobile number", errors);
        //  validateUtil.checkNull(request, "email", "Email", errors);

        validateUtil.checkLength(errors, request, "fname", "First Name", 255, 1);
        validateUtil.checkLength(errors, request, "mname", "middle Name", 255, 0);
        validateUtil.checkLength(errors, request, "lname", "Last Name", 255, 0);
        validateUtil.checkLength(errors, request, "lno", "Licence number", 255, 0);
        validateUtil.checkLength(errors, request, "add1", "Address 1", 255, 0);
        validateUtil.checkLength(errors, request, "add2", "Address 2", 255, 0);
        validateUtil.checkLength(errors, request, "add3", "Address 3", 255, 0);
        validateUtil.checkLength(errors, request, "city", "City", 255, 0);
        validateUtil.checkLength(errors, request, "pin", "Pincode", 255, 0);
        validateUtil.checkLength(errors, request, "state", "State", 255, 0);
        validateUtil.checkLength(errors, request, "email", "Email", 255, 0);
        validateUtil.checkLength(errors, request, "mob", "Mobile", 255, 1);
        validateUtil.checkLength(errors, request, "status", "Status", 255, 0);

        CommonUtils commonUtils = new CommonUtils();
        if (!commonUtils.validatePhoneNumber(request.getParameter("mob"))) {
            errors.add("Please enter proper Phone number ");
        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
//        if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
//            MaDriver checkEmail = drService.checkEmail(Constant.ACTIVE.toString(), request.getParameter("email"));
//            if (checkEmail != null && !maDriver.getEmail().equals(checkEmail.getEmail())) {
//                errors.add("Email is already exist");
//            }
//        }
        MaDriver checkMobile = drService.checkMobile(Constant.ACTIVE.toString(), request.getParameter("mob"));
        if (checkMobile != null && !maDriver.getMobile().equals(checkMobile.getMobile())) {
            errors.add("Mobile is already exist");
        }
        if (!maDriver.getStatus().equals(request.getParameter("status"))) {
            MaDriver maDriver1 = drService.findoneEdit( maDriver.getId());
            if (maDriver1 == null) {
                errors.add(" This Driver is assigned in job. For Inactive, please first remove Driver from job.");
            }
        }
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }

        maDriver.setStatus(validateUtil.getStringValue(request.getParameter("status")));

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
        //maDriver.setStatus(Constant.ACTIVE.toString());

        drService.save(maDriver);
        return "redirect:/driver/drivelist?m=e";
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
