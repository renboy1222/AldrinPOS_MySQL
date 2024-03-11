/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.StockInEntry;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface StockInEntryDAO {

    public Long getMaxId();

    public Long getMaxIdForRef();

//    add StockInEntry
    public void addStockInEntry(StockInEntry stockInEntry);

//    update StrockInEntry
    public List<StockInEntry> selectStockInEntry(StockInEntry stockInEntry);

    public Long getStockInEntryIDByProcessId(Long id);

    public void updateStockInEntryProcess(StockInEntry stockInEntry);

    public Boolean validateQtyRunOut(Long productId, Integer quantity);

    public StockInEntry findProductByBarCode(StockInEntry stockInEntry);
    
    public StockInEntry findProductById(StockInEntry stockInEntry);
}
