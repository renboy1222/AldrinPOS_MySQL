/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.Unit;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author ALDRIN B. C.
 */
public interface UnitDAO {

    public Long getMaxId();
    
//    add Unit
    public void addUnit(Unit unit);
    
//    update Unit
    public void updateUnit(Unit unit);
    
//    delete Unit
    public void deleteUnit(Unit unit);
    
//    list of Unit
    public List<Unit> selectUnit();

    public void comboBoxUnit();
}
