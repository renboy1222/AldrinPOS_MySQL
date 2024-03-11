/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.Invoice;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface InvoiceDAO {

    public Long getMaxId();
    
    public Long getMaxIdForRef();

//    add Invoice
    public void addInvoice(Invoice invoice);

//    update Invoice
    public void updateInvoice(Invoice inovice);

//    delete Invoice
    public void deleteInvoice(Invoice invoice);

//    list of Invoice
    public List<Invoice> selectInvoice();

    public void comboBoxInvoice();

}
