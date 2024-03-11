/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.Product;
import java.util.List;

/**
 *
 * @author ALDRIN B. C.
 */
public interface ProductDAO {
     public Long getMaxId();
     
//    add Product
    public void addProduct(Product product);
    
//    update Product
    public void updateProduct(Product product);
    
//    delete Product
    public void deleteProduct(Product product);
    
//    list of Product
    public List<Product> selectProduct();
    
    public void comboBoxProduct();
}
