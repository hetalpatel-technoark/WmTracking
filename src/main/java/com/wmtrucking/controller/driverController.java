/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.wmtrucking.entities.MaDriver;
import com.wmtrucking.entities.MaDriverHistory;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.DriverHistoryService;
import com.wmtrucking.services.driverService;
import com.wmtrucking.utils.CommonUtils;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.OTPutils;
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

@RequestMapping(value = "/driver")
@Scope("request")
@Controller
public class driverController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    DriverHistoryService driverHistoryService;
    @Autowired
    driverService drService;

    @ModelAttribute(value = "driver")
    public void driver(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/drivelist", method = RequestMethod.GET)
    public String drivelist(HttpServletRequest request, Model model) {

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

        validateUtil.checkNull(request, "driverid", "Driver Id", errors);
        validateUtil.checkNull(request, "driverLicNo", "Driver License Number", errors);
        validateUtil.checkLength(errors, request, "cmpname", "Trucking Company Name", 255, 0);
        validateUtil.checkLength(errors, request, "fname", "First Name", 255, 1);
        validateUtil.checkLength(errors, request, "mname", "middle Name", 255, 0);
//        validateUtil.checkLength(errors, request, "lname", "Last Name", 255, 0);
        validateUtil.checkNull(request, "lname", "Last Name", errors);
        // validateUtil.checkNull(request, "countryCode", "Country Code", errors);

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
//        if (request.getParameter("driverid") != null && !commonUtils.isBigInteger(request.getParameter("driverid"))) {
//            errors.add("Please Enter Proper Driver Id");
//        }
        String mob = request.getParameter("mob").replace("-", "");
        if (!commonUtils.validatePhoneNumber(mob)) {
            errors.add("Please enter proper Phone number ");
        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
        if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
            MaDriver checkEmail = drService.checkEmail(Constant.ACTIVE.toString(), request.getParameter("email"));
            if (checkEmail != null) {
                errors.add("Email is already exist");
            }
        }

        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Driver/Create";
        }
//        MaDriver checkMobile = drService.checkMobile(Constant.ACTIVE.toString(), mob);
//        if (checkMobile != null) {
//            errors.add("Mobile is already exist");
//            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
//            return "Driver/Create";
//        }
        MaDriver checkDriverId = drService.checkDriverId(Constant.ACTIVE.toString(), request.getParameter("driverid"));
        if (checkDriverId != null) {
            errors.add("This Driver Id is already exist");
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Driver/Create";
        }
        try {
            MaDriver maDriver = new MaDriver();
            maDriver.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
            maDriver.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
            maDriver.setLastname(validateUtil.getStringValue(request.getParameter("lname")));

            maDriver.setDrivernumber(validateUtil.getStringValue(request.getParameter("driverid")));
            maDriver.setDriverlicense(validateUtil.getStringValue(request.getParameter("driverLicNo")));
            maDriver.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));

            maDriver.setLicensenumber(validateUtil.getStringValue(request.getParameter("lno")));
            maDriver.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
            maDriver.setCity(validateUtil.getStringValue(request.getParameter("city")));
            maDriver.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
            maDriver.setState(validateUtil.getStringValue(request.getParameter("state")));
            maDriver.setEmail(validateUtil.getStringValue(request.getParameter("email")));
            maDriver.setMobile(mob);
            //  maDriver.setCountrycode(validateUtil.getStringValue(request.getParameter("countryCode")));
            //maDriver.setStatus(Constant.ACTIVE.toString());
            maDriver.setCreateddate(new Date());
            maDriver.setStatus(validateUtil.getStringValue(request.getParameter("status")));

            OTPutils oTPutils = new OTPutils();
            String code = String.valueOf(oTPutils.otp(6));
            String sms_text = "Your password is : " + code;
            System.out.println("Your password is ....." + code);
            //oTPutils.sendSMS(request.getParameter("countryCode") + mobile, sms_text);
            oTPutils.sendSMS(mob, sms_text);

            maDriver.setPassword(code);

            drService.save(maDriver);
        } catch (Exception e) {

            errors.add("Mobile is already exist");
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            return "Driver/Create";
        }
        return "redirect:/driver/drivelist?m=c";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String Delete(HttpServletRequest request, Model model, @PathVariable("id") String id) {

        MaDriver maDrivers = drService.findoneDelete(Constant.ACTIVE.toString(), Long.parseLong(id));

        if (maDrivers != null) {
//            maDriver.setStatus(Constant.DETETED.toString());
//            drService.save(maDriver);

            MaDriverHistory maDriver = new MaDriverHistory();
            maDriver.setFirstname(maDrivers.getFirstname());
            maDriver.setMiddlename(maDrivers.getMiddlename());
            maDriver.setLastname(maDrivers.getLastname());

            maDriver.setDrivernumber(maDrivers.getDrivernumber());
            maDriver.setDriverlicense(maDrivers.getDriverlicense());
            maDriver.setCompanyname(maDrivers.getCompanyname());

            maDriver.setLicensenumber(maDrivers.getLicensenumber());
            maDriver.setAddress1(maDrivers.getAddress1());
            maDriver.setCity(maDrivers.getCity());
            maDriver.setPincode(maDrivers.getPincode());
            maDriver.setState(maDrivers.getState());
            maDriver.setEmail(maDrivers.getEmail());
            maDriver.setMobile(maDrivers.getMobile());
            maDriver.setCreateddate(new Date());
            maDriver.setStatus(maDrivers.getStatus());
            maDriver.setPassword(maDrivers.getPassword());
            driverHistoryService.save(maDriver);

            drService.deleted(maDrivers);
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

        validateUtil.checkNull(request, "driverid", "Driver Id", errors);
        validateUtil.checkNull(request, "driverLicNo", "Driver License Number", errors);
        validateUtil.checkLength(errors, request, "cmpname", "Trucking Company Name", 255, 0);
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
        //validateUtil.checkNull(request, "countryCode", "Country Code", errors);

        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }
        CommonUtils commonUtils = new CommonUtils();
//        if (!commonUtils.isBigInteger(request.getParameter("driverid"))) {
//            errors.add("Please Enter Proper Driver Id");
//        }
//        if (!commonUtils.validatePhoneNumber(request.getParameter("mob"))) {
//            errors.add("Please enter proper Phone number ");
//        }
        if (!commonUtils.checkLong(request.getParameter("pin"))) {
            errors.add("Please enter proper Pincode ");
        }
        String mob = request.getParameter("mob").replace("-", "");
//        MaDriver checkMobile = drService.checkMobile(Constant.ACTIVE.toString(), mob);
//        if (checkMobile != null && !maDriver.getMobile().equals(checkMobile.getMobile())) {
//            errors.add("Mobile is already exist");
//        }
        MaDriver checkDriverId = drService.checkDriverId(Constant.ACTIVE.toString(), request.getParameter("driverid"));

        if (checkDriverId != null && !checkDriverId.getDrivernumber().equals(maDriver.getDrivernumber())) {
            errors.add("This Driver Id is already exist");
        }

        if (!maDriver.getStatus().equals(request.getParameter("status"))) {
            MaDriver maDriver1 = drService.findoneEdit(maDriver.getId());
            if (maDriver1 == null) {
                errors.add(" This Driver is assigned in job. For Inactive, please first remove Driver from job.");
            }
        }

        if (request.getParameter("email") != null && !request.getParameter("email").equals("")) {
            MaDriver checkEmail = drService.checkEmail(Constant.ACTIVE.toString(), request.getParameter("email"));
            if (checkEmail != null && !maDriver.getEmail().equals(checkEmail.getEmail())) {
                errors.add("Email is already exist");
            }
        }
        if (errors.size() > 0) {
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }
        try {
            maDriver.setStatus(validateUtil.getStringValue(request.getParameter("status")));

            maDriver.setFirstname(validateUtil.getStringValue(request.getParameter("fname")));
            maDriver.setMiddlename(validateUtil.getStringValue(request.getParameter("mname")));
            maDriver.setLastname(validateUtil.getStringValue(request.getParameter("lname")));

            maDriver.setDrivernumber(validateUtil.getStringValue(request.getParameter("driverid")));
            maDriver.setDriverlicense(validateUtil.getStringValue(request.getParameter("driverLicNo")));
            maDriver.setCompanyname(validateUtil.getStringValue(request.getParameter("cmpname")));

            maDriver.setLicensenumber(validateUtil.getStringValue(request.getParameter("lno")));
            maDriver.setAddress1(validateUtil.getStringValue(request.getParameter("add1")));
            maDriver.setCity(validateUtil.getStringValue(request.getParameter("city")));
            maDriver.setPincode(validateUtil.getStringValue(request.getParameter("pin")));
            maDriver.setState(validateUtil.getStringValue(request.getParameter("state")));
            maDriver.setEmail(validateUtil.getStringValue(request.getParameter("email")));
            maDriver.setMobile(validateUtil.getStringValue(mob));

            drService.save(maDriver);
        } catch (Exception e) {

            errors.add("Mobile is already exist");
            model.addAttribute(Constant.ERRORPARAM.toString(), errors);
            model.addAttribute("maDriver", maDriver);
            return "Driver/Edit";
        }
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
