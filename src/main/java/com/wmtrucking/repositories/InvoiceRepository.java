/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaInvoice;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Scope(value = "request")
public interface InvoiceRepository extends JpaRepository<MaInvoice, Long> {

    @Query(nativeQuery = true, value = "select u.* from ma_invoice u where u.status=?1 and jobid=?2 ORDER BY u.id desc")
    List<MaInvoice> list(String satus, Long jobid);

    @Query(nativeQuery = true, value = "select u.* from ma_invoice u where u.status=?1 and u.id=?2")
    MaInvoice findoneinvoice(String satus, Long id);

    @Query(nativeQuery = true, value = "select u.* from ma_invoice u where u.status=?1 and u.jobid=?2 and u.driverid=?3")
    MaInvoice findDriverinvoice(String satus, Long jobid, Long driverid);
}
