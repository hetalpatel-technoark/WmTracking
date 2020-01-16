/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.wmtrucking.entities.MaInvoice;
import com.wmtrucking.exception.UnAthorizedUserException;
import com.wmtrucking.services.invoiceService;
import com.wmtrucking.utils.Constant;
import com.wmtrucking.utils.InternalRenderer;
import com.wmtrucking.utils.MaJWT;
import com.wmtrucking.utils.SessionUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
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

/**
 *
 * @author Admin
 */
@RequestMapping(value = "/invoice")
@Scope("request")
@Controller
public class InvoiceController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    invoiceService invoiceService;
    @Autowired
    InternalRenderer internalRenderer;
    @Autowired
    MaJWT maJWT;

    @ModelAttribute(value = "invoice")
    public void customer(HttpServletRequest request, Model model) throws UnAthorizedUserException {
        if (sessionUtils.getSessionValue(request, Constant.AUTHSESSION.toString()) == null) {
            throw new UnAthorizedUserException("");
        }
    }

    @RequestMapping(value = "/list/{jobid}", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model, @PathVariable("jobid") Long jobid) {

        List<MaInvoice> maInvoice = invoiceService.list(Constant.ACTIVE.toString(), jobid);
        model.addAttribute("maInvoice", maInvoice);

        return "Invoice/List";
    }

    @RequestMapping(value = "/pdf/{invoiceid}", method = RequestMethod.GET)
    public void pdf(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model,
            @PathVariable("invoiceid") Long invoiceid) throws IOException, DocumentException, UnAthorizedUserException {
        MaInvoice maInvoice = invoiceService.findoneinvoice(Constant.ACTIVE.toString(), invoiceid);
        request.setAttribute("maInvoice", maInvoice);
        try {
            String evalView = internalRenderer.evalView(request, response, model, locale, "Invoice/pdfview");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + invoiceid + ".pdf");

            StringBuilder result = new StringBuilder();
            result.append(evalView);

            Document document = new Document(PageSize.A4, 25, 25, 25, 0);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
// add header and footer
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
//            pdfWriter.setPageEvent(event);

            document.open();

            XMLWorkerHelper xMLWorkerHelper = XMLWorkerHelper.getInstance();
            ByteArrayInputStream bis
                    = new ByteArrayInputStream(result.toString().getBytes());

            xMLWorkerHelper.parseXHtml(pdfWriter, document, bis);

            document.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/pdfAPI/{jobid}", method = RequestMethod.GET)
    public void pdfAPI(HttpServletRequest request, HttpServletResponse response, Locale locale, Model model, @PathVariable("jobid") Long jobid)
            throws IOException, DocumentException, UnAthorizedUserException {

        if (!maJWT.checkHeadersWithAuth(request)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String driverId = maJWT.getId(maJWT.getJwt(request), request);
        if (driverId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        MaInvoice maInvoice = invoiceService.findDriverinvoice(Constant.ACTIVE.toString(), jobid, Long.parseLong(driverId));
        if (maInvoice != null) {
            request.setAttribute("maInvoice", maInvoice);
            try {
                String evalView = internalRenderer.evalView(request, response, model, locale, "Invoice/pdfview");
                System.out.println("evalView::::::: " + evalView);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=" + maInvoice.getId() + ".pdf");

                StringBuilder result = new StringBuilder();
                result.append(evalView);

                Document document = new Document(PageSize.A4, 25, 25, 25, 0);
                PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
                document.open();

                XMLWorkerHelper xMLWorkerHelper = XMLWorkerHelper.getInstance();
                ByteArrayInputStream bis
                        = new ByteArrayInputStream(result.toString().getBytes());

                xMLWorkerHelper.parseXHtml(pdfWriter, document, bis);

                document.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace();
        ex.printStackTrace(new PrintWriter(errors));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

}
