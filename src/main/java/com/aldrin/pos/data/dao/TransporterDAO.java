/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;


import com.aldrin.pos.model.Transporter;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface TransporterDAO {
    
    public Long getMaxId();
    
//    add Transporter
    public void addTransporter(Transporter transporter);
    
//    update Transporter
    public void updateTransporter(Transporter transporter);
    
//    delete Transporter
    public void deleteTransporter(Transporter transporter);
    
//    list of the Transporter
    public List<Transporter> selectTransporter();


    public Transporter findPhotoByTransporterId(Transporter transporter);
    
    public void comboBoxTransporter();
    
}
