/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.aldrin.pos.data.dao;

import com.aldrin.pos.model.Category;
import java.util.ArrayList;

/**
 *
 * @author ALDRIN B. C.
 */
public interface CategoryDAO {

    public Long getMaxId();

//    add Category
    public void addCategory(Category category);

//    update Category
    public void updateCategory(Category category);

//    delete Category
    public void deleteCategory(Category category);

//    list of Category
    public ArrayList<Category> selectCategory();

    public void comboBoxCategory();

}
