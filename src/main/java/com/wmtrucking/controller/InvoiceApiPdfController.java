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
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Admin
 */
@RequestMapping(value = "/pdfAPI")
@Scope("request")
@Controller
public class InvoiceApiPdfController {

    @Autowired
    SessionUtils sessionUtils;
    @Autowired
    invoiceService invoiceService;
    @Autowired
    InternalRenderer internalRenderer;
    @Autowired
    MaJWT maJWT;

    @RequestMapping(value = "/{jobid}", method = RequestMethod.GET)
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
        request.setAttribute("maInvoice", maInvoice);
        try {
            String evalView = internalRenderer.evalView(request, response, model, locale, "Invoice/pdfview");
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
