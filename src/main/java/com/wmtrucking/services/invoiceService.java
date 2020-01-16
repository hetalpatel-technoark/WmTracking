/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.services;

import com.wmtrucking.entities.MaInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wmtrucking.repositories.InvoiceRepository;
import java.util.List;

/**
 *
 * @author Admin
 */
@Service
public class invoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public List<MaInvoice> list(String satus, Long jobid) {
        return invoiceRepository.list(satus, jobid);
    }

    public MaInvoice findoneinvoice(String satus, Long id) {
        return invoiceRepository.findoneinvoice(satus, id);
    }

    public MaInvoice findDriverinvoice(String satus, Long jobid, Long driverid) {
        return invoiceRepository.findDriverinvoice(satus, jobid, driverid);
    }
}
