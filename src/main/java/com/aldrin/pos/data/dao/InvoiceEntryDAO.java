/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.InvoiceEntry;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface InvoiceEntryDAO {
     public Long getMaxId();
     
//    add InvoiceEntry
    public void addInvoiceEntry(InvoiceEntry InvoiceEntry);
    
//    update InvoiceEntry
    public List<InvoiceEntry> selectInvoiceEntry(InvoiceEntry invoice);
    
    public void comboBoxInvoiceStart();
    
    public void comboBoxInvoiceEnd();

   

}
