/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.StockIn;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface StockInDAO {

    public Long getMaxId();

    public Long getMaxIdForRef();

//    add Stock-in
    public void addStockIn(StockIn stockIn);

//    update Stock-in
    public void updateStockIn(StockIn stockIn);

//    delete Stock-in
    public void deleteStockIn(StockIn stockIn);

//    list of Stock-in
    public List<StockIn> selectStockIn();

    public void comboBoxStockIn();

}
